package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.hotel.hotelapp.entity.Hotel;
import com.hotel.hotelapp.service.HotelService;


@RestController
@RequestMapping("/hotels")
public class HotelController 
{
    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return service.createHotel(hotel);
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return service.getAllHotels();
    }
}
