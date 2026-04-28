package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.hotel.hotelapp.entity.Hotel;
import com.hotel.hotelapp.entity.Room;
import com.hotel.hotelapp.repository.HotelRepository;
import com.hotel.hotelapp.repository.RoomRepository; 

@Service
public class RoomService 
{

    private final RoomRepository repo;

    public RoomService(RoomRepository repo)
    {
        this.repo = repo;
    }

    public Room createRoom(Room room)
    {
        return repo.save(room);
    }
    public List<Room> getAllRooms()
    {
        return repo.findAll();
    }

}
