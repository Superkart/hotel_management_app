package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.hotel.hotelapp.entity.Booking;
import com.hotel.hotelapp.exception.BookingConflictException;
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
        List<Booking> conflicts = repo.findOverlappingBookings(booking.getRoom().getRoomNumber(), booking.getStartDate(), booking.getEndDate());

        if (!conflicts.isEmpty()) 
            {
                throw new BookingConflictException("Room is already booked for this date range");
            }
            
        return repo.save(booking);
    }

    public List<Booking> getAllBookings() 
    {
        return repo.findAll();
    }
}