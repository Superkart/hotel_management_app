package com.hotel.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapp.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> 
{
    
}