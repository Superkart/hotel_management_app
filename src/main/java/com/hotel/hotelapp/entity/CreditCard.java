package com.hotel.hotelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;;

@Entity
public class CreditCard 
{
    @Id private String cardNumber;
    
    @ManyToOne private Client client;
    @ManyToOne private Address billingAddress;

    public CreditCard()
    {

    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public Address getBillingAddress()
    {
        return billingAddress;
    }

    public Client getClient() {
        return client;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public void setBillingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

