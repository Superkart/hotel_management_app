package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.hotel.hotelapp.entity.Booking;
import com.hotel.hotelapp.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController 
{

    private final BookingService service;

    public BookingController(BookingService service) 
    {
        this.service = service;
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) 
    {
        return service.createBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() 
    {
        return service.getAllBookings();
    }
}
