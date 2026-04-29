package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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

    @GetMapping("/my")
    public List<Booking> getMyBookings(@RequestParam String email)
    {
        return service.getMyBookings(email);
    }

    @GetMapping("/top-clients")
    public List<Object[]> getTopClients(@RequestParam int k)
    {
        return service.getTopClients(k);
    }

    @GetMapping("/room-bookings")
    public List<Object[]> getRoomBookingCounts()
    {
        return service.getRoomBookingCounts();
    }

    @GetMapping("/client-spending")
    public List<Object[]> getClientSpending()
    {
        return service.getClientSpending();
    }

    @PostMapping("/auto")
    public Object autoBook(
            @RequestParam Long hotelId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String email,
            @RequestParam double price)
    {
        return service.autoBook(hotelId, LocalDate.parse(startDate), LocalDate.parse(endDate), email, price);
    }
}
