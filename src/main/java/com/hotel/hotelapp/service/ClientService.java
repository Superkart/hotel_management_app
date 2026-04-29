package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.hotel.hotelapp.entity.Client;
import com.hotel.hotelapp.repository.ClientRepository;

@Service
public class ClientService
{
    private final ClientRepository repo;

    public ClientService(ClientRepository repo)
    {
        this.repo = repo;
    }

    public Client createClient(Client client)
    {
        return repo.save(client);
    }

    public List<Client> getAllClients()
    {
        return repo.findAll();
    }

    public Optional<Client> login(String email)
    {
        return repo.findById(email);
    }

    public void deleteClient(String email)
    {
        repo.deleteById(email);
    }

    public List<Object[]> getClientsInCity1BookedInCity2(String city1, String city2)
    {
        return repo.findClientsInCity1BookedInCity2(city1, city2);
    }
}
