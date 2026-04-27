package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.hotel.hotelapp.entity.Hotel;
import com.hotel.hotelapp.repository.HotelRepository;

@Service
public class HotelService 
{

    private final HotelRepository repo;

    public HotelService(HotelRepository repo)
    {
        this.repo = repo;
    }

    public Hotel createHotel(Hotel hotel)
    {
        return repo.save(hotel);
    }

    public List<Hotel> getAllHotels()
    {
        return repo.findAll();
    }

}
