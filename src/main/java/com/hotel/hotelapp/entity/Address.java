package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(AddressID.class)
public class Address 
{
    @Id private String streetName;
    @Id private int streetNumber;
    @Id private String city;

    public Address()
    {

    }

    public Address(String streetName, int streetNumber, String city)
    {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
    }

    public String getStreetName()
    {
        return streetName;
    }

    public int getStreetNumber()
    {
        return streetNumber;
    }

    public String getCity()
    {
        return city;
    }

    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    public void setStreetNumber(int streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

}
