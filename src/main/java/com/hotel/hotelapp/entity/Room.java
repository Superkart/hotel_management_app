package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Room 
{
 
    @Id private Long roomNumber;
    private int windows;
    private int renovationYear;
    private boolean elevatorAccess;

    @ManyToOne private Hotel hotel;


    public Room()
    {

    }

    
    // Getters

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

}
