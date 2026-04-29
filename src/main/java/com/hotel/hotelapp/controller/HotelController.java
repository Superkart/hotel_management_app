package com.hotel.hotelapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.hotel.hotelapp.entity.Hotel;
import com.hotel.hotelapp.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController
{
    private final HotelService service;

    public HotelController(HotelService service)
    {
        this.service = service;
    }

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel)
    {
        return service.createHotel(hotel);
    }

    @GetMapping
    public List<Hotel> getAllHotels()
    {
        return service.getAllHotels();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel)
    {
        Hotel updated = service.updateHotel(id, hotel);

        if (updated != null)
        {
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id)
    {
        service.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    public List<Object[]> getHotelStats()
    {
        return service.getHotelStats();
    }

    @GetMapping("/problematic")
    public List<Object[]> getProblematicHotels()
    {
        return service.getProblematicHotels();
    }
}
