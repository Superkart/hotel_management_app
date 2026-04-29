package com.hotel.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hotel.hotelapp.entity.Hotel;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long>
{
    @Query(value = """
            SELECT h.hotel_id, h.name,
                   (SELECT COUNT(*) FROM room rm JOIN booking b ON b.room_room_number = rm.room_number WHERE rm.hotel_hotel_id = h.hotel_id) as total_bookings,
                   (SELECT AVG(r.rating) FROM review r WHERE r.hotel_hotel_id = h.hotel_id) as avg_rating
            FROM hotel h
            """, nativeQuery = true)
    List<Object[]> getHotelStats();

    @Query(value = """
            SELECT DISTINCT h.hotel_id, h.name
            FROM hotel h
            WHERE h.address_city = 'Chicago'
            AND h.hotel_id IN (
                SELECT r2.hotel_hotel_id
                FROM review r2
                GROUP BY r2.hotel_hotel_id
                HAVING AVG(r2.rating) < 2
            )
            AND (
                SELECT COUNT(DISTINCT b2.client_email)
                FROM booking b2
                JOIN room rm2 ON rm2.room_number = b2.room_room_number
                WHERE rm2.hotel_hotel_id = h.hotel_id
                AND b2.client_email NOT IN (
                    SELECT ca.client_email
                    FROM client_address ca
                    WHERE ca.city = 'Chicago'
                )
            ) >= 2
            """, nativeQuery = true)
    List<Object[]> getProblematicHotels();
}
