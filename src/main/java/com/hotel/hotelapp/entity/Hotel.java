package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Hotel 
{

    @Id private Long hotelId;
    private String name;
    

    @ManyToOne private Address address;
    
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

    public Address getAddress() 
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

    public List<Room> getRooms() 
    {
        return rooms;
    }

    public void setRooms(List<Room> rooms) 
    {
        this.rooms = rooms;
    }

    public void setAddress(Address address) 
    {
        this.address = address;
    }

}


