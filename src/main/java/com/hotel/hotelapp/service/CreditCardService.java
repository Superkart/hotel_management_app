package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.hotel.hotelapp.entity.CreditCard;
import com.hotel.hotelapp.repository.CreditCardRepository;

@Service
public class CreditCardService {

    private final CreditCardRepository repo;

    public CreditCardService(CreditCardRepository repo) 
    {
        this.repo = repo;
    }

    public CreditCard createCard(CreditCard card) 
    {
        return repo.save(card);
    }

    public List<CreditCard> getAllCards() 
    {
        return repo.findAll();
    }
}