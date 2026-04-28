package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.hotel.hotelapp.entity.Booking;
import com.hotel.hotelapp.repository.BookingRepository;


@Service
public class BookingService {

    private final BookingRepository repo;

    public BookingService(BookingRepository repo) 
    {
        this.repo = repo;
    }

    public Booking createBooking(Booking booking) 
    {
        return repo.save(booking);
    }

    public List<Booking> getAllBookings() 
    {
        return repo.findAll();
    }
}