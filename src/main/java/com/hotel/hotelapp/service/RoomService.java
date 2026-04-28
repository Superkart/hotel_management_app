package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Room> getAllAvailableRooms(LocalDate startDate, LocalDate endDate)
    {
        List<Room> allRooms = roomRepo.findAll();
        List<Room> availableRooms = new ArrayList<>();

    for(Room room : allRooms)
        {
            List<Booking> conflictBooking = bookingRepo.findOverlappingBookings(room.getRoomNumber(), startDate, endDate);
            
            if(conflictBooking.isEmpty())
                {
                    availableRooms.add(room);
                }

        }
        return availableRooms;
    }

}
