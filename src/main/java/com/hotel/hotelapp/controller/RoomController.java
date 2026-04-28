package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.hotel.hotelapp.entity.Room;
import com.hotel.hotelapp.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController 
{
    
    private final RoomService service;

    public RoomController(RoomService service) 
    {
        this.service = service;
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) 
    {
        return service.createRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() 
    {
        return service.getAllRooms();
    }


}
