package com.hotel.hotelapp.exception;

public class ReviewNotAllowedException extends RuntimeException
{

    public ReviewNotAllowedException(String message)
    {
        super(message);
    }
}
