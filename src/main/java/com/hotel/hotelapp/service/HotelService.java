package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    public Hotel updateHotel(Long id, Hotel updated)
    {
        Optional<Hotel> existing = repo.findById(id);

        if (existing.isPresent())
        {
            Hotel hotel = existing.get();
            hotel.setName(updated.getName());
            hotel.setAddress(updated.getAddress());
            return repo.save(hotel);
        }

        return null;
    }

    public void deleteHotel(Long id)
    {
        repo.deleteById(id);
    }

    public List<Object[]> getHotelStats()
    {
        return repo.getHotelStats();
    }

    public List<Object[]> getProblematicHotels()
    {
        return repo.getProblematicHotels();
    }
}
