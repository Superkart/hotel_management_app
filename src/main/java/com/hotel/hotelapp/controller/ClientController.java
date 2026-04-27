package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

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
}
