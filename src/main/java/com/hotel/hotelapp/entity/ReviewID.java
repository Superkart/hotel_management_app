package com.hotel.hotelapp.entity;

import java.io.Serializable;
import java.util.Objects;

public class ReviewID implements Serializable
{
    private Long hotelId;
    private int reviewId;

    public ReviewID()
    {

    }

    public ReviewID(Long hotelId, int reviewId)
    {
        this.hotelId = hotelId;
        this.reviewId = reviewId;
    }

    public Long getHotelId()
    {
        return hotelId;
    }

    public int getReviewId()
    {
        return reviewId;
    }

    public void setHotelId(Long hotelId)
    {
        this.hotelId = hotelId;
    }

    public void setReviewId(int reviewId)
    {
        this.reviewId = reviewId;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof ReviewID))
        {
            return false;
        }

        ReviewID other = (ReviewID) obj;
        return Objects.equals(this.hotelId, other.hotelId) && Objects.equals(this.reviewId, other.reviewId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.hotelId, this.reviewId);
    }
}
