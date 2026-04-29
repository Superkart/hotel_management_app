package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.hotel.hotelapp.entity.Review;
import com.hotel.hotelapp.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController 
{
    private final ReviewService reviewService;

    public ReviewController(ReviewService service) 
    {
        this.reviewService = service;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) 
    {
        return reviewService.createReview(review);
    }

    @GetMapping
    public List<Review> getAllReviews() 
    {
        return reviewService.getAllReviews();
    }
    
}
