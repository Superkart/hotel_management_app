package com.hotel.hotelapp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotel.hotelapp.entity.Booking;
import com.hotel.hotelapp.entity.Hotel;
import com.hotel.hotelapp.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>
{
    @Query("""
            SELECT b FROM Booking b
            WHERE b.room.roomNumber = :roomNumber
            AND b.room.hotel.hotelId = :hotelId
            AND b.startDate <= :endDate
            AND b.endDate >= :startDate
            """)
    List<Booking> findOverlappingBookings(
            @Param("roomNumber") Long roomNumber,
            @Param("hotelId") Long hotelId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
            SELECT b.client.name, b.client.email, COUNT(b)
            FROM Booking b
            GROUP BY b.client.name, b.client.email
            ORDER BY COUNT(b) DESC
            """)
    List<Object[]> findTopClients(Pageable pageable);

    @Query(value = """
            SELECT r.room_number, r.hotel_hotel_id,
                   (SELECT COUNT(*) FROM booking b WHERE b.room_room_number = r.room_number) AS booking_count
            FROM room r
            """, nativeQuery = true)
    List<Object[]> getRoomBookingCounts();

    List<Booking> findByClientEmail(String email);

    @Query(value = """
            SELECT c.name, c.email, SUM(b.price_per_day * (b.end_date - b.start_date)) as total_spent
            FROM client c
            JOIN booking b ON b.client_email = c.email
            GROUP BY c.name, c.email
            ORDER BY total_spent DESC
            """, nativeQuery = true)
    List<Object[]> getClientSpending();

    @Query("""
            SELECT r FROM Room r
            WHERE r.hotel.hotelId = :hotelId
            AND NOT EXISTS (
                SELECT 1 FROM Booking b
                WHERE b.room = r
                AND b.startDate <= :endDate
                AND b.endDate >= :startDate
            )
            """)
    List<Room> findAvailableRoomsInHotel(
            @Param("hotelId") Long hotelId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
            SELECT DISTINCT r.hotel FROM Room r
            WHERE r.hotel.hotelId != :hotelId
            AND NOT EXISTS (
                SELECT 1 FROM Booking b
                WHERE b.room = r
                AND b.startDate <= :endDate
                AND b.endDate >= :startDate
            )
            """)
    List<Hotel> findAlternativeHotels(
            @Param("hotelId") Long hotelId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
            SELECT COUNT(b) FROM Booking b
            WHERE b.client.email = :email
            AND b.room.hotel.hotelId = :hotelId
            """)
    long countBookingsByClientAndHotel(
            @Param("email") String email,
            @Param("hotelId") Long hotelId
    );
}
