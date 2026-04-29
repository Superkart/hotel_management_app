package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Hotel 
{

    @Id private Long hotelId;
    private String name;
    private String address;
    
    @OneToMany(mappedBy = "hotel")
    @JsonIgnore private List<Room> rooms;

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

    public List<Room> getRooms() 
    {
        return rooms;
    }

    public void setRooms(List<Room> rooms) 
    {
        this.rooms = rooms;
    }
}


