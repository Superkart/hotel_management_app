package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.hotel.hotelapp.entity.Review;
import com.hotel.hotelapp.repository.ReviewRepository;

@Service
public class ReviewService 
{
 
    private final ReviewRepository reviewRepo;

    public ReviewService(ReviewRepository reviewRepo)
    {
        this.reviewRepo = reviewRepo;
    }

    public Review createReview(Review review)
    {
        return reviewRepo.save(review);
    }

    public List<Review> getAllReviews()
    {
        return reviewRepo.findAll();        
    }
}
