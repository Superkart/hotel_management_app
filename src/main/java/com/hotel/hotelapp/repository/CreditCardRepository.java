package com.hotel.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapp.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> 
{
    
}