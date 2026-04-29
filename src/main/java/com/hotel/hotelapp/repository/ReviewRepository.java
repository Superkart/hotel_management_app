package com.hotel.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapp.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> 
{
    
}
