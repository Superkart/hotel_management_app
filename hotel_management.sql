CREATE TABLE manager (
    ssn VARCHAR(11) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE address (
    street_name VARCHAR(100),
    street_number INTEGER,
    city VARCHAR(50),
    PRIMARY KEY (street_name, street_number, city)
);

CREATE TABLE hotel (
    hotel_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address_street_name VARCHAR(100),
    address_street_number INTEGER,
    address_city VARCHAR(50),
    FOREIGN KEY (address_street_name, address_street_number, address_city)
        REFERENCES address(street_name, street_number, city)
);

CREATE TABLE room (
    room_number INTEGER PRIMARY KEY,
    windows INTEGER,
    elevator_access BOOLEAN,
    renovation_year INTEGER,
    hotel_hotel_id INTEGER NOT NULL,
    FOREIGN KEY (hotel_hotel_id)
        REFERENCES hotel(hotel_id)
);

CREATE TABLE client (
    email VARCHAR(100) PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE client_address (
    client_email VARCHAR(100),
    street_name VARCHAR(100),
    street_number INTEGER,
    city VARCHAR(50),
    PRIMARY KEY (client_email, street_name, street_number, city),
    FOREIGN KEY (client_email)
        REFERENCES client(email),
    FOREIGN KEY (street_name, street_number, city)
        REFERENCES address(street_name, street_number, city)
);

CREATE TABLE credit_card (
    card_number VARCHAR(50) PRIMARY KEY,
    client_email VARCHAR(100),
    billing_address_street_name VARCHAR(100),
    billing_address_street_number INTEGER,
    billing_address_city VARCHAR(50),
    FOREIGN KEY (client_email)
        REFERENCES client(email),
    FOREIGN KEY (billing_address_street_name, billing_address_street_number, billing_address_city)
        REFERENCES address(street_name, street_number, city)
);

CREATE TABLE booking (
    booking_id SERIAL PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    price_per_day DECIMAL(10, 2),
    client_email VARCHAR(100),
    room_room_number INTEGER,
    FOREIGN KEY (client_email)
        REFERENCES client(email),
    FOREIGN KEY (room_room_number)
        REFERENCES room(room_number)
);

CREATE TABLE review (
    review_id SERIAL PRIMARY KEY,
    message TEXT,
    rating INTEGER,
    hotel_hotel_id INTEGER,
    client_email VARCHAR(100),
    FOREIGN KEY (hotel_hotel_id)
        REFERENCES hotel(hotel_id),
    FOREIGN KEY (client_email)
        REFERENCES client(email)
);
