package com.hotel.hotelapp.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Client 
{

    @Id private String email;
    private String name;

    @ManyToMany
    @JoinTable(
        name = "client_address",
        joinColumns = @JoinColumn(name = "client_email"),
        inverseJoinColumns = {
            @JoinColumn(name = "streetName"),
            @JoinColumn(name = "streetNumber"),
            @JoinColumn(name = "city")
        }
    )
    private List<Address> addresses;

    @OneToMany(mappedBy = "client")
    private List<CreditCard> creditCards;

    @OneToMany(mappedBy = "client")
    private List<Booking> bookings;

    public Client()
    {

    }

    // Getters
    public String getName()
    {
        return name;
    }
    public String getEmail() 
    {
        return email;
    }
    public List<Address> getAddresses() 
    {
        return addresses;
    }
    public List<CreditCard> getCreditCards()
    {
        return creditCards;
    }
    public List<Booking> getBookings() 
    {
        return bookings;
    }

    // Setters
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddresses(List<Address> addresses) 
    {
        this.addresses = addresses;
    }
        public void setCreditCards(List<CreditCard> creditCards)
    {
        this.creditCards = creditCards;
    }

    public void setBookings(List<Booking> bookings) 
    {
        this.bookings = bookings;
    }
    
}
