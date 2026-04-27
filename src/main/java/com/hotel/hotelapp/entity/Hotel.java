package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hotel 
{

    @Id private Long hotelId;
    private String name;
    private String address;
    
    public Hotel()
    {

    }

    // Getters

    public Long getHotelId()
    {
        return hotelId;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    // Setters

    public void setHotelId(Long hotelId)
    {
        this.hotelId = hotelId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}


