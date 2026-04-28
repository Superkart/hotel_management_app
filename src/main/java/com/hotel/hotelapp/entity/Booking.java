package com.hotel.hotelapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id private Long bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double pricePerDay;

    @ManyToOne private Client client;

    @ManyToOne private Room room;

    public Booking() 
    {

    }

    // Getters
    public Long getBookingId() 
    { 
        return bookingId; 
    }
    public LocalDate getStartDate() 
    { 
        return startDate; 
    }
    public LocalDate getEndDate() 
    { 
        return endDate; 
    }
    public double getPricePerDay() 
    { 
        return pricePerDay;
    }
    public Client getClient()
    { 
        return client; 
    }
    public Room getRoom() 
    { 
        return room; 
    }

    // Setters
    public void setBookingId(Long bookingId) 
    { 
        this.bookingId = bookingId; 
    }
    public void setStartDate(LocalDate startDate) 
    { 
        this.startDate = startDate; 
    }
    public void setEndDate(LocalDate endDate) 
    { 
        this.endDate = endDate; 
    }
    public void setPricePerDay(double pricePerDay) 
    { 
        this.pricePerDay = pricePerDay; 
    }
    public void setClient(Client client) 
    { 
        this.client = client; 
    }
    public void setRoom(Room room) 
    { 
        this.room = room; 
    }
}