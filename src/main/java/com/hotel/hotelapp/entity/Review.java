package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
public class Review 
{
    @Id private Long reviewId;

    @ManyToOne private Hotel hotel;

    @ManyToOne private Client client;

    private String message;
    private int rating;


    public Review()
    {

    }

    public Long getReviewId()
    {
        return reviewId;
    }

    public String getMessage()
    {
        return message;
    }

    public int getRating()
    {
        return rating;
    }

    public Hotel getHotel()
    {
        return hotel;
    }

    public Client getClient()
    {
        return client;
    }

    public void setReviewId(Long reviewId)
    {
        this.reviewId = reviewId;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public void setHotel(Hotel hotel)
    {
        this.hotel = hotel;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }
}
