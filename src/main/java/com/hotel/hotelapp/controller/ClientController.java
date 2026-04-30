package com.hotel.hotelapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.hotel.hotelapp.entity.Client;
import com.hotel.hotelapp.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController
{
    private final ClientService service;

    public ClientController(ClientService service)
    {
        this.service = service;
    }

    @PostMapping
    public Client createClient(@RequestBody Client client)
    {
        return service.createClient(client);
    }

    @GetMapping
    public List<Client> getAllClients()
    {
        return service.getAllClients();
    }

    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestParam String email)
    {
        Optional<Client> client = service.login(email);

        if (client.isPresent())
        {
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.status(401).build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<Client> updateClient(@PathVariable String email, @RequestBody Client client)
    {
        Client updated = service.updateClient(email, client);
        if (updated != null) return ResponseEntity.ok(updated);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteClient(@PathVariable String email)
    {
        service.deleteClient(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/city-query")
    public List<Object[]> getClientsInCity1BookedInCity2(
            @RequestParam String city1,
            @RequestParam String city2)
    {
        return service.getClientsInCity1BookedInCity2(city1, city2);
    }
}
