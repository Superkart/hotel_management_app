package com.hotel.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapp.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String>
{

    
} 