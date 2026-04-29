package com.hotel.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapp.entity.Address;
import com.hotel.hotelapp.entity.AddressID;

public interface AddressRepository extends JpaRepository<Address, AddressID> 
{
    
}