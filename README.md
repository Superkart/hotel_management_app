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