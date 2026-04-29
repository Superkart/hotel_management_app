package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.hotel.hotelapp.entity.Address;
import com.hotel.hotelapp.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController 
{

    private final AddressService service;

    public AddressController(AddressService service) 
    {
        this.service = service;
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address) 
    {
        return service.createAddress(address);
    }

    @GetMapping
    public List<Address> getAllAddresses() 
    {
        return service.getAllAddresses();
    }
}