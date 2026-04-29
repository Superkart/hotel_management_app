package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.hotel.hotelapp.entity.CreditCard;
import com.hotel.hotelapp.service.CreditCardService;

@RestController
@RequestMapping("/cards")
public class CreditCardController 
{

    private final CreditCardService service;

    public CreditCardController(CreditCardService service) 
    {
        this.service = service;
    }

    @PostMapping
    public CreditCard createCard(@RequestBody CreditCard card) 
    {
        return service.createCard(card);
    }

    @GetMapping
    public List<CreditCard> getAllCards() 
    {
        return service.getAllCards();
    }
}