package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Client 
{

    @Id
    private String email;
    private String name;

    public Client()
    {

    }

    public String getName()
    {
        return name;
    }
    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
}
