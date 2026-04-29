CREATE TABLE Manager (
	SSN VARCHAR(11) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL
);

CREATE TABLE  Address(
	street_name	VARCHAR(100),
	street_number INTEGER,
	city VARCHAR(50),
	PRIMARY KEY (street_name, street_number, city)
);

CREATE TABLE Hotel (
	hotel_id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	street_name VARCHAR(100),
	street_number INTEGER,
	city VARCHAR(50),
	manager_SSN VARCHAR(11),
	FOREIGN KEY (street_name, street_number, city)
		REFERENCES Address(street_name, street_number, city),
);

CREATE TABLE Room (
	room_number INTEGER PRIMARY KEY,
	windows INTEGER,
    elevatorAccess BOOLEAN,
    last_renovation_year INTEGER,
    hotel_id INTEGER NOT NULL,
    FOREIGN KEY (hotel_id) 
        REFERENCES Hotel(hotel_id),
);

CREATE TABLE Client (
	email VARCHAR(100) PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE Credit_Card (
	card_number VARCHAR(50) PRIMARY KEY,
	email VARCHAR(100),
	street_name VARCHAR(100), 
	street_number INTEGER, 
	city VARCHAR(50),
	FOREIGN KEY (email)
		REFERENCES Client(email),
	FOREIGN KEY (street_name, street_number, city)
		REFERENCES Address(street_name, street_number, city)
);

CREATE TABLE Booking (
	booking_id SERIAL PRIMARY KEY,
	start_date DATE,
	end_date DATE,
	price_per_day DECIMAL(10, 2),
	email VARCHAR(100),
	hotel_id INTEGER,
	room_number INTEGER,
	FOREIGN KEY (email)
		REFERENCES Client(email),
	FOREIGN KEY (hotel_id, room_number)
		REFERENCES Room(hotel_id, room_number)
);

CREATE TABLE Review (
	review_id INTEGER PRIMARY KEY,
    message TEXT,
    rating INTEGER,
    hotel_id INTEGER,
    email VARCHAR(100),
    FOREIGN KEY (hotel_id)
        REFERENCES Hotel(hotel_id),
    FOREIGN KEY (email)
        REFERENCES Client(email),
);

CREATE TABLE Client_Address (
    email VARCHAR(100),
    street_name VARCHAR(100),
    street_number INTEGER,
    city VARCHAR(50),
    FOREIGN KEY (email) 
        REFERENCES Client(email),
    FOREIGN KEY (street_name, street_number, city) 
        REFERENCES Address(street_name, street_number, city),
    PRIMARY KEY (email, street_name, street_number, city)
);