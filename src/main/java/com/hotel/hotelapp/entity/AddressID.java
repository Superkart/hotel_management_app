package com.hotel.hotelapp.entity;

import java.io.Serializable;
import java.util.Objects;

public class AddressID implements Serializable
{
    private String streetName;
    private int streetNumber;
    private String city;

    public AddressID()
    {

    }

    public AddressID(String streetName, int streetNumber, String city)
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

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof AddressID))
        {
            return false;
        }

        AddressID aID = (AddressID) obj;
        return this.streetNumber == aID.streetNumber && Objects.equals(this.streetName, aID.streetName) && Objects.equals(this.city, aID.city);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.streetName, this.streetNumber, this.city);
    }
}
