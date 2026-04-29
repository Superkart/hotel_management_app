package com.hotel.hotelapp.controller;

import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{roomNumber}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomNumber, @RequestBody Room room)
    {
        Room updated = service.updateRoom(roomNumber, room);

        if (updated != null)
        {
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomNumber)
    {
        service.deleteRoom(roomNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms(@RequestParam String startDate, @RequestParam String endDate)
    {
        return service.getAllAvailableRooms(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}
