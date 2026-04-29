# Hotel App Demo Run Order

This file is the exact order to demo the project in Postman. Follow it from top to bottom so the required data exists before you run the later tests.

## 1. Prerequisites

1. Start PostgreSQL.
2. Make sure the database `hotel_management` exists.
3. Confirm `src/main/resources/application.properties` has the correct database password.
4. Start the app:

```bash
mvn spring-boot:run
```

Base URL:

```text
http://localhost:8080
```

## 2. Important Note About What Is Implemented

The code supports the following demo flows well:

1. Manager register and login.
2. Add, update, delete hotels.
3. Add, update, delete rooms.
4. Remove clients.
5. Top-k clients by bookings.
6. Room booking counts.
7. Hotel booking stats and average ratings.
8. City-to-city client query.
9. Problematic hotel report.
10. Client register, login, bookings, auto-booking, reviews.

The project does not currently have a separate endpoint for updating client profile data, and client credit cards are created with a separate endpoint after registration. Also, the available-room endpoint returns rooms, but not the hotel name in the response body.

## 3. Demo Order

### Step 1 - Create the addresses first

These are required before creating hotels, clients, and billing addresses.

POST `http://localhost:8080/addresses`

```json
{ "streetName": "Main St", "streetNumber": 100, "city": "Chicago" }
```

```json
{ "streetName": "Elm St", "streetNumber": 50, "city": "New York" }
```

```json
{ "streetName": "Lake Shore Dr", "streetNumber": 10, "city": "Chicago" }
```

Verify:

GET `http://localhost:8080/addresses`

### Step 2 - Register a manager and log in

POST `http://localhost:8080/managers`

```json
{ "ssn": "111-22-3333", "name": "John Manager", "email": "john.manager@test.com" }
```

Login:

POST `http://localhost:8080/managers/login?ssn=111-22-3333`

Expected result: the manager object returned with status 200.

### Step 3 - Create a hotel

POST `http://localhost:8080/hotels`

```json
{
  "name": "Grand Hotel",
  "address": { "streetName": "Main St", "streetNumber": 100, "city": "Chicago" }
}
```

Save the returned `hotelId`.

Verify:

GET `http://localhost:8080/hotels`

Update hotel:

PUT `http://localhost:8080/hotels/1`

```json
{
  "name": "Grand Hotel Updated",
  "address": { "streetName": "Main St", "streetNumber": 100, "city": "Chicago" }
}
```

### Step 4 - Create rooms for that hotel

POST `http://localhost:8080/rooms`

```json
{
  "roomNumber": 101,
  "windows": 2,
  "renovationYear": 2020,
  "elevatorAccess": true,
  "hotel": { "hotelId": 1 }
}
```

```json
{
  "roomNumber": 102,
  "windows": 1,
  "renovationYear": 2019,
  "elevatorAccess": false,
  "hotel": { "hotelId": 1 }
}
```

Verify:

GET `http://localhost:8080/rooms`

Update a room:

PUT `http://localhost:8080/rooms/101`

```json
{
  "windows": 3,
  "renovationYear": 2022,
  "elevatorAccess": true,
  "hotel": { "hotelId": 1 }
}
```

### Step 5 - Register a client with an address

POST `http://localhost:8080/clients`

```json
{
  "email": "alice@test.com",
  "name": "Alice",
  "addresses": [
    { "streetName": "Elm St", "streetNumber": 50, "city": "New York" }
  ]
}
```

Register a second client with a Chicago address for later comparison:

POST `http://localhost:8080/clients`

```json
{
  "email": "bob@test.com",
  "name": "Bob",
  "addresses": [
    { "streetName": "Lake Shore Dr", "streetNumber": 10, "city": "Chicago" }
  ]
}
```

Login client:

POST `http://localhost:8080/clients/login?email=alice@test.com`

### Step 6 - Add credit cards after the client exists

POST `http://localhost:8080/cards`

```json
{
  "cardNumber": "4111111111111111",
  "client": { "email": "alice@test.com" },
  "billingAddress": { "streetName": "Elm St", "streetNumber": 50, "city": "New York" }
}
```

```json
{
  "cardNumber": "5555444433332222",
  "client": { "email": "alice@test.com" },
  "billingAddress": { "streetName": "Main St", "streetNumber": 100, "city": "Chicago" }
}
```

Verify:

GET `http://localhost:8080/cards`

### Step 7 - Check available rooms before any booking exists

GET `http://localhost:8080/rooms/available?startDate=2026-06-01&endDate=2026-06-05`

This should show rooms that are free for the date range.

### Step 8 - Create a booking for an available room

POST `http://localhost:8080/bookings`

```json
{
  "room": { "roomNumber": 101 },
  "client": { "email": "alice@test.com" },
  "startDate": "2026-06-01",
  "endDate": "2026-06-05",
  "pricePerDay": 150.0
}
```

Important test:

Send the same booking again to prove the overlap check.

Expected result: `409 Conflict`.

Verify bookings:

GET `http://localhost:8080/bookings`

Get the client’s bookings:

GET `http://localhost:8080/bookings/my?email=alice@test.com`

### Step 9 - Use automatic booking

If the hotel still has a free room, this will book one automatically.

POST `http://localhost:8080/bookings/auto?hotelId=1&startDate=2026-08-01&endDate=2026-08-05&email=alice@test.com&price=120`

If the hotel is full, the response will be a list of alternative hotels.

### Step 10 - Add more bookings so the manager analytics look good

Create at least one more booking for Bob so the analytics endpoints have more than one client.

POST `http://localhost:8080/bookings`

```json
{
  "room": { "roomNumber": 102 },
  "client": { "email": "bob@test.com" },
  "startDate": "2026-07-01",
  "endDate": "2026-07-04",
  "pricePerDay": 180.0
}
```

### Step 11 - Submit a review only after the stay exists

POST `http://localhost:8080/reviews`

```json
{
  "client": { "email": "alice@test.com" },
  "hotel": { "hotelId": 1 },
  "rating": 5,
  "message": "Great stay!"
}
```

Test the rejection case too:

POST `http://localhost:8080/reviews`

```json
{
  "client": { "email": "newperson@test.com" },
  "hotel": { "hotelId": 1 },
  "rating": 1,
  "message": "This should fail because the client has no booking here."
}
```

Expected result: `403 Forbidden`.

### Step 12 - Show manager reports

Top-k clients:

GET `http://localhost:8080/bookings/top-clients?k=2`

Room booking counts:

GET `http://localhost:8080/bookings/room-bookings`

Client spending:

GET `http://localhost:8080/bookings/client-spending`

Hotel stats:

GET `http://localhost:8080/hotels/stats`

City query:

GET `http://localhost:8080/clients/city-query?city1=New York&city2=Chicago`

Problematic hotels:

GET `http://localhost:8080/hotels/problematic`

### Step 13 - Delete data if you need to show remove operations

Delete a client:

DELETE `http://localhost:8080/clients/bob@test.com`

Delete a room:

DELETE `http://localhost:8080/rooms/102`

Delete a hotel:

DELETE `http://localhost:8080/hotels/1`

## 4. Demo Tips

1. Always create addresses before hotels and clients.
2. Always create a hotel before rooms.
3. Always create a client before cards, bookings, and reviews.
4. Always create a booking before trying to post a review.
5. Use the same hotel and room numbers in the same order during the demo so the payloads stay simple.
6. If a delete fails, it is usually because there are dependent rows in bookings, reviews, or rooms.

## 5. Quick Feature Checklist For Submission

1. Manager registration and login.
2. Hotel insert, update, delete, and stats.
3. Room insert, update, delete, and availability.
4. Client registration, login, and delete.
5. Credit card creation.
6. Booking creation, conflict detection, my bookings, auto-booking.
7. Review creation only after a stay.
8. Analytics endpoints for top clients, room bookings, spending, city query, and problematic hotels.