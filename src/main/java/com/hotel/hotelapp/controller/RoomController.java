package com.hotel.hotelapp.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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


    @GetMapping("/available")
    public List<Room> getAvailableRooms(@RequestParam String startDate, @RequestParam String endDate)
    {
        return service.getAllAvailableRooms(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

}
