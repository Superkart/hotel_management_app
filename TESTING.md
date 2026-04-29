# Hotel App — Postman Testing Guide

## 1. PostgreSQL Setup

1. Install PostgreSQL 17 and open pgAdmin.
2. Create a database named `hotel_management`.
3. Make sure the postgres user password matches `application.properties` (default: `1451`).
   - To reset: right-click the `postgres` role → Properties → Password.

`application.properties` key settings:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_management
spring.datasource.username=postgres
spring.datasource.password=1451
spring.jpa.hibernate.ddl-auto=update
```
Hibernate will create all tables automatically on first run.

---

## 2. Start the Server

```
mvn spring-boot:run
```

Server starts on `http://localhost:8080`.

---

## 3. Test Order (must follow — foreign keys depend on it)

### Step 1 — Create Addresses
Addresses are referenced by both Hotels and Clients. Create them first.

**POST** `http://localhost:8080/addresses`
```json
{ "streetName": "Main St", "streetNumber": 100, "city": "Chicago" }
```
```json
{ "streetName": "Elm St", "streetNumber": 50, "city": "New York" }
```

---

### Step 2 — Create a Hotel
**POST** `http://localhost:8080/hotels`
```json
{
  "name": "Grand Hotel",
  "address": { "streetName": "Main St", "streetNumber": 100, "city": "Chicago" }
}
```
Note the returned `hotelId` (e.g., `1`).

**GET** `http://localhost:8080/hotels` — verify it appears.

**PUT** `http://localhost:8080/hotels/1`
```json
{
  "name": "Grand Hotel Updated",
  "address": { "streetName": "Main St", "streetNumber": 100, "city": "Chicago" }
}
```

---

### Step 3 — Create a Room
**POST** `http://localhost:8080/rooms`
```json
{
  "roomNumber": 101,
  "windows": 2,
  "renovationYear": 2020,
  "elevatorAccess": true,
  "hotel": { "hotelId": 1 }
}
```

**GET** `http://localhost:8080/rooms` — verify.

**PUT** `http://localhost:8080/rooms/101`
```json
{
  "windows": 3,
  "renovationYear": 2022,
  "elevatorAccess": true,
  "hotel": { "hotelId": 1 }
}
```

---

### Step 4 — Register a Manager
**POST** `http://localhost:8080/managers`
```json
{ "ssn": "111-22-3333", "name": "John Manager", "hotelId": 1 }
```

**POST** `http://localhost:8080/managers/login?ssn=111-22-3333`
— Returns the manager object on success, 401 if SSN not found.

---

### Step 5 — Register a Client
**POST** `http://localhost:8080/clients`
```json
{
  "email": "alice@test.com",
  "name": "Alice",
  "addresses": [
    { "streetName": "Elm St", "streetNumber": 50, "city": "New York" }
  ]
}
```

**POST** `http://localhost:8080/clients/login?email=alice@test.com`
— Returns the client object, 401 if not found.

---

### Step 6 — Create a Booking
**POST** `http://localhost:8080/bookings`
```json
{
  "room": { "roomNumber": 101 },
  "client": { "email": "alice@test.com" },
  "startDate": "2026-06-01",
  "endDate": "2026-06-05",
  "pricePerDay": 150.0
}
```

**Conflict test** — POST the same date range again → expect `409 Conflict`.

**GET** `http://localhost:8080/bookings` — all bookings.

**GET** `http://localhost:8080/bookings/my?email=alice@test.com` — client's own bookings.

---

### Step 7 — Post a Review
Must have a booking at that hotel first.

**POST** `http://localhost:8080/reviews`
```json
{
  "client": { "email": "alice@test.com" },
  "hotel": { "hotelId": 1 },
  "rating": 5,
  "comment": "Great stay!"
}
```

**Invalid review test** — use a client with no booking at that hotel → expect `403 Forbidden`.

---

### Step 8 — Analytics Endpoints

**GET** `http://localhost:8080/bookings/top-clients?k=3`
— Returns top 3 clients by number of bookings.

**GET** `http://localhost:8080/bookings/room-bookings`
— Returns each room and its booking count.

**GET** `http://localhost:8080/bookings/client-spending`
— Returns each client's total spending (days × pricePerDay).

**GET** `http://localhost:8080/hotels/stats`
— Returns each hotel's total bookings and average review rating.

---

### Step 9 — Auto Booking
Finds any available room in the given hotel for the date range, books it automatically.
Falls back to a list of alternative hotels if the requested one is fully booked.

**POST** `http://localhost:8080/bookings/auto?hotelId=1&startDate=2026-08-01&endDate=2026-08-05&email=alice@test.com&price=120`

---

### Step 10 — City Query
Returns clients whose home address is in `city1` and who have a booking at a hotel in `city2`.

**GET** `http://localhost:8080/clients/city-query?city1=New York&city2=Chicago`

---

### Step 11 — Problematic Hotels
Hotels in Chicago with average rating < 2 that have been booked by 2 or more clients from outside Chicago.

**GET** `http://localhost:8080/hotels/problematic`

To set up test data for this:
1. Create a Chicago hotel with bad reviews (rating 1).
2. Create clients with non-Chicago addresses.
3. Have those clients book rooms in that hotel.
4. Hit the endpoint.

---

### Step 12 — Delete Client
**DELETE** `http://localhost:8080/clients/alice@test.com`
— Returns `204 No Content`.

---

## 4. Troubleshooting

| Problem | Fix |
|---|---|
| 500 on POST /bookings | Make sure the room exists and its hotel FK is set |
| 404 on POST /hotels | Create the address record first |
| 409 on POST /bookings | Date range overlaps an existing booking for that room |
| 403 on POST /reviews | Client has no booking at that hotel |
| Empty city-query result | Verify client_address table and hotel address_city column in pgAdmin |
| Tables not created | Check DB name in application.properties matches pgAdmin |
