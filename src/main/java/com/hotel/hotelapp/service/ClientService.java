package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.hotel.hotelapp.entity.Address;
import com.hotel.hotelapp.entity.AddressID;
import com.hotel.hotelapp.entity.Client;
import com.hotel.hotelapp.entity.CreditCard;
import com.hotel.hotelapp.repository.AddressRepository;
import com.hotel.hotelapp.repository.ClientRepository;
import com.hotel.hotelapp.repository.CreditCardRepository;

@Service
public class ClientService
{
    private final ClientRepository repo;
    private final AddressRepository addressRepo;
    private final CreditCardRepository cardRepo;

    public ClientService(ClientRepository repo, AddressRepository addressRepo, CreditCardRepository cardRepo)
    {
        this.repo = repo;
        this.addressRepo = addressRepo;
        this.cardRepo = cardRepo;
    }

    private Address findOrCreate(Address addr)
    {
        AddressID id = new AddressID(addr.getStreetName(), addr.getStreetNumber(), addr.getCity());
        return addressRepo.findById(id).orElseGet(() -> addressRepo.save(addr));
    }

    public Client createClient(Client client)
    {
        if (client.getAddresses() != null)
        {
            List<Address> persisted = new ArrayList<>();
            for (Address a : client.getAddresses()) persisted.add(findOrCreate(a));
            client.setAddresses(persisted);
        }

        List<CreditCard> cards = client.getCreditCards() != null
            ? new ArrayList<>(client.getCreditCards()) : new ArrayList<>();
        client.setCreditCards(new ArrayList<>());

        Client saved = repo.save(client);

        for (CreditCard card : cards)
        {
            if (card.getBillingAddress() != null) card.setBillingAddress(findOrCreate(card.getBillingAddress()));
            card.setClient(saved);
            cardRepo.save(card);
        }

        return saved;
    }

    public Client updateClient(String email, Client updated)
    {
        return repo.findById(email).map(existing ->
        {
            if (updated.getName() != null) existing.setName(updated.getName());

            if (updated.getAddresses() != null)
            {
                List<Address> persisted = new ArrayList<>();
                for (Address a : updated.getAddresses()) persisted.add(findOrCreate(a));
                existing.setAddresses(persisted);
            }

            Client saved = repo.save(existing);

            if (updated.getCreditCards() != null)
            {
                for (CreditCard card : updated.getCreditCards())
                {
                    if (card.getBillingAddress() != null) card.setBillingAddress(findOrCreate(card.getBillingAddress()));
                    card.setClient(saved);
                    cardRepo.save(card);
                }
            }

            return saved;
        }).orElse(null);
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
