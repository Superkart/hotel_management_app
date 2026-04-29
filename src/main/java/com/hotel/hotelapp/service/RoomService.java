package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import com.hotel.hotelapp.entity.Booking;
import com.hotel.hotelapp.entity.Room;
import com.hotel.hotelapp.repository.BookingRepository;
import com.hotel.hotelapp.repository.RoomRepository;

@Service
public class RoomService
{
    private final RoomRepository roomRepo;
    private final BookingRepository bookingRepo;

    public RoomService(RoomRepository roomRepo, BookingRepository bookingRepo)
    {
        this.roomRepo = roomRepo;
        this.bookingRepo = bookingRepo;
    }

    public Room createRoom(Room room)
    {
        return roomRepo.save(room);
    }

    public List<Room> getAllRooms()
    {
        return roomRepo.findAll();
    }

    public Room updateRoom(Long roomNumber, Room updated)
    {
        Optional<Room> existing = roomRepo.findById(roomNumber);

        if (existing.isPresent())
        {
            Room room = existing.get();
            room.setWindows(updated.getWindows());
            room.setRenovationYear(updated.getRenovationYear());
            room.setElevatorAccess(updated.isElevatorAccess());
            return roomRepo.save(room);
        }

        return null;
    }

    public void deleteRoom(Long roomNumber)
    {
        roomRepo.deleteById(roomNumber);
    }

    public List<Room> getAllAvailableRooms(LocalDate startDate, LocalDate endDate)
    {
        List<Room> allRooms = roomRepo.findAll();
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : allRooms)
        {
            List<Booking> conflictBooking = bookingRepo.findOverlappingBookings(
                room.getRoomNumber(),
                room.getHotel().getHotelId(),
                startDate,
                endDate
            );

            if (conflictBooking.isEmpty())
            {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }
}
