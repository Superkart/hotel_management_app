package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.hotel.hotelapp.entity.Review;
import com.hotel.hotelapp.exception.ReviewNotAllowedException;
import com.hotel.hotelapp.repository.BookingRepository;
import com.hotel.hotelapp.repository.ReviewRepository;

@Service
public class ReviewService
{
    private final ReviewRepository reviewRepo;
    private final BookingRepository bookingRepo;

    public ReviewService(ReviewRepository reviewRepo, BookingRepository bookingRepo)
    {
        this.reviewRepo = reviewRepo;
        this.bookingRepo = bookingRepo;
    }

    public Review createReview(Review review)
    {
        long count = bookingRepo.countBookingsByClientAndHotel(
            review.getClient().getEmail(),
            review.getHotel().getHotelId()
        );

        if (count == 0)
        {
            throw new ReviewNotAllowedException("Client has not booked at this hotel");
        }

        return reviewRepo.save(review);
    }

    public List<Review> getAllReviews()
    {
        return reviewRepo.findAll();
    }
}
