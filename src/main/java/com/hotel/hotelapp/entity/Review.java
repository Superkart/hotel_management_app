package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
@IdClass(ReviewID.class)
public class Review 
{
    @Id private int reviewId;
    private String message;
    private int rating;

    @Id @ManyToOne private Hotel hotel;
    @ManyToOne private Client client;

    public Review()
    {

    }

    public int getId()
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

    public void setId(int reviewId)
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
