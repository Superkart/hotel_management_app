package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Room 
{
 
    @Id private Long roomNumber;
    private int windows;
    private int renovationYear;
    private boolean elevatorAccess;

    @ManyToOne  @JsonIgnore private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    public Room()
    {

    }

    
    // Getters

    public List<Booking> getBookings() 
    {
        return bookings;
    }

    public Long getRoomNumber() 
    {
        return roomNumber;
    }

    public int getWindows() 
    {
        return windows;
    }

    public int getRenovationYear() 
    {
        return renovationYear;
    }

    public boolean isElevatorAccess()
    {
        return elevatorAccess;
    }

    public Hotel getHotel() 
    {
        return hotel;
    }

    // Setters
    public void setRoomNumber(Long roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public void setWindows(int windows) 
    {
        this.windows = windows;
    }

    public void setRenovationYear(int renovationYear) 
    {
        this.renovationYear = renovationYear;
    }

    public void setElevatorAccess(boolean elevatorAccess) 
    {
        this.elevatorAccess = elevatorAccess;
    }

    public void setHotel(Hotel hotel) 
    {
        this.hotel = hotel;
    }

    public void setBookings(List<Booking> bookings) 
    {
        this.bookings = bookings;
    }

}
