package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Manager 
{
    @Id private String ssn;
    private String name;
    private String email;

    public Manager()
    {

    }

    public String getSsn()
    {
        return ssn;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
