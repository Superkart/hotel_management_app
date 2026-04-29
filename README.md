# Hotel Management App

A Spring Boot REST API for managing hotels, rooms, clients, bookings, and reviews — built for CS480 Database Systems.

## Tech Stack

- Java 21
- Spring Boot 3.x / Spring Data JPA / Hibernate
- PostgreSQL 17
- Postman for API testing

## Architecture

```
controller/   HTTP endpoints
service/      business logic
repository/   JPQL + native SQL queries
entity/       JPA-mapped tables
exception/    global error handling
```

## Data Model

| Entity | Key Fields |
|---|---|
| Hotel | hotelId, name, address (FK) |
| Room | roomNumber, windows, renovationYear, elevatorAccess, hotel (FK) |
| Address | streetName, streetNumber, city (composite PK) |
| Client | email (PK), name, addresses (M2M), creditCards (1toM) |
| CreditCard | cardNumber, client (FK), billingAddress (FK) |
| Manager | ssn (PK), name, hotelId |
| Booking | bookingId, startDate, endDate, pricePerDay, client (FK), room (FK) |
| Review | reviewId, rating, comment, client (FK), hotel (FK) |

## API Reference

### Addresses
| Method | URL | Body |
|---|---|---|
| POST | `/addresses` | `{ streetName, streetNumber, city }` |
| GET | `/addresses` | — |

### Hotels
| Method | URL | Notes |
|---|---|---|
| POST | `/hotels` | address must exist first |
| GET | `/hotels` | — |
| PUT | `/hotels/{id}` | — |
| DELETE | `/hotels/{id}` | — |
| GET | `/hotels/stats` | hotelId, name, total bookings, avg rating |
| GET | `/hotels/problematic` | Chicago hotels avg < 2, booked by 2+ non-Chicago clients |

### Rooms
| Method | URL | Notes |
|---|---|---|
| POST | `/rooms` | hotel must exist first |
| GET | `/rooms` | — |
| PUT | `/rooms/{roomNumber}` | — |
| DELETE | `/rooms/{roomNumber}` | — |

### Managers
| Method | URL | Notes |
|---|---|---|
| POST | `/managers` | `{ ssn, name, hotelId }` |
| POST | `/managers/login?ssn=` | 401 if not found |

### Clients
| Method | URL | Notes |
|---|---|---|
| POST | `/clients` | include addresses array |
| GET | `/clients` | — |
| POST | `/clients/login?email=` | 401 if not found |
| DELETE | `/clients/{email}` | — |
| GET | `/clients/city-query?city1=&city2=` | clients in city1 who booked a hotel in city2 |

### Bookings
| Method | URL | Notes |
|---|---|---|
| POST | `/bookings` | 409 if date range overlaps existing booking |
| GET | `/bookings` | — |
| GET | `/bookings/my?email=` | client's own bookings |
| POST | `/bookings/auto?hotelId=&startDate=&endDate=&email=&price=` | books first available room; returns alternative hotels on full |
| GET | `/bookings/top-clients?k=` | top k clients by booking count |
| GET | `/bookings/room-bookings` | each room and its total booking count |
| GET | `/bookings/client-spending` | each client's total spend |

### Reviews
| Method | URL | Notes |
|---|---|---|
| POST | `/reviews` | 403 if client has no booking at that hotel |
| GET | `/reviews` | — |

### Credit Cards
| Method | URL | — |
|---|---|---|
| POST | `/credit-cards` | — |
| GET | `/credit-cards` | — |

## Running the App

1. Create a PostgreSQL database named `hotel_management`.
2. Set your postgres password in `src/main/resources/application.properties`.
3. Start the server:

```bash
mvn spring-boot:run
```

Hibernate creates all tables automatically on first run.

See **TESTING.md** for the full ordered Postman walkthrough.

## Error Codes

| Code | Meaning |
|---|---|
| 409 | Booking date conflict |
| 403 | Review not allowed (no prior booking) |
| 401 | Login failed |
| 404 | Resource not found |
