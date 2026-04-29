package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.hotel.hotelapp.entity.Address;
import com.hotel.hotelapp.repository.AddressRepository;

@Service
public class AddressService 
{

    private final AddressRepository repo;

    public AddressService(AddressRepository repo) 
    {
        this.repo = repo;
    }

    public Address createAddress(Address address) 
    {
        return repo.save(address);
    }

    public List<Address> getAllAddresses() 
    {
        return repo.findAll();
    }
}