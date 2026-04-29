package com.hotel.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hotel.hotelapp.entity.Client;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, String>
{
    @Query(value = """
            SELECT DISTINCT c.name, c.email
            FROM client c
            JOIN client_address ca ON ca.client_email = c.email
            JOIN address a ON a.street_name = ca.street_name
                AND a.street_number = ca.street_number
                AND a.city = ca.city
            JOIN booking b ON b.client_email = c.email
            JOIN room r ON r.room_number = b.room_room_number
            JOIN hotel h ON h.hotel_id = r.hotel_hotel_id
            JOIN address ha ON ha.street_name = h.address_street_name
                AND ha.street_number = h.address_street_number
                AND ha.city = h.address_city
            WHERE a.city = :city1
            AND ha.city = :city2
            """, nativeQuery = true)
    List<Object[]> findClientsInCity1BookedInCity2(
            @Param("city1") String city1,
            @Param("city2") String city2
    );
}
