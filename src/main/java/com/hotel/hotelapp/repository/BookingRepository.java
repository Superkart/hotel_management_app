package com.hotel.hotelapp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotel.hotelapp.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> 
{
    @Query("""
            SELECT b FROM Booking b
            WHERE b.room.roomNumber = :roomNumber
            AND b.startDate <= :endDate
            AND b.endDate >= :startDate
            """)

        List<Booking> findOverlappingBookings(
            @Param("roomNumber") Long roomNumber,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
            SELECT b.client.email, COUNT(b)
            FROM Booking b
            GROUP BY b.client.email
            ORDER BY COUNT(b) DESC
            """)
            
        List<Object[]> findTopClients(Pageable pageable);


    @Query("""
            SELECT b.room.roomNumber, b.room.hotel.hotelId, COUNT(b)
            FROM Room r
            LEFT JOIN Booking b ON b.room = r
            GROUP BY r.roomNumber, r.hotel.hotelId
            """)

        List<Object[]> getRoomBookingCounts();
}