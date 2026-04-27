package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
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

}



