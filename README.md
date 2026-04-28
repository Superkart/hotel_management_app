# Hotel Management App

## Overview
Backend system for managing hotels, rooms, clients, and bookings. The application includes core booking logic such as preventing overlapping reservations and searching for available rooms within a date range.

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA / Hibernate
- H2 Database (development)
- PostgreSQL (optional)
- Postman for API testing

## Architecture
- Controller: Handles API requests and responses  
- Service: Contains business logic  
- Repository: Handles database operations  
- Entity: Represents database tables  

## Features
- Create and manage clients, hotels, and rooms  
- Booking system with overlap validation  
- Search available rooms by date range  

## Running the Application
```bash
mvn spring-boot:run
```

## Requirements Status

### Data Model

Completed

- Hotel entity with id, name, and address
- Room entity with room number, windows, renovation year, and elevator access, linked to Hotel
- Client entity with email and name
- Booking entity with booking id, start date, end date, and price per day, linked to Client and Room
- Booking overlap validation to prevent double-booking the same room

Not Completed

- Manager entity with SSN, name, and email
- Address entity with street name, street number, and city
- Credit card entity linked to a client and a billing address
- Review entity with message and rating, linked to a hotel and a client
- Client to Address relationship (a client can have multiple addresses)
- Client to Credit Card relationship (a client can have multiple credit cards)
- Hotel address should reference the Address entity rather than a plain string
- Room primary key should be composite (hotel id and room number), not room number alone

---

### Manager Features

Not Completed

- Manager registration with name, SSN, and email, and login via SSN
- Insert, update, and delete hotels and rooms (create exists, update and delete do not)
- Remove a client from the system
- Retrieve top k clients by number of bookings
- List all rooms with their total booking count
- Per-hotel report showing name, total bookings, and average rating
- Find clients who have an address in city C1 and have booked a hotel in city C2
- Report problematic Chicago hotels with average rating below 2, booked by at least two clients who have no address in Chicago
- Per-client report showing total amount spent on bookings

---

### Client Features

Completed

- Search for available rooms by date range
- Book a specific room for a given date range with overlap validation

Not Completed

- Client registration with name, email, one or more addresses, and one or more credit cards
- Client login via email
- Update client information including name, addresses, and credit cards
- Automatic booking at a specified hotel for a date range, with fallback suggestions if no room is available
- View all bookings for the logged-in client including room, hotel name, and cost
- Submit a review for a hotel only if the client has a prior booking there


