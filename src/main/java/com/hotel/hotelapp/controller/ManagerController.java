package com.hotel.hotelapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.hotel.hotelapp.entity.Manager;
import com.hotel.hotelapp.service.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController
{
    private final ManagerService service;

    public ManagerController(ManagerService service)
    {
        this.service = service;
    }

    @PostMapping
    public Manager registerManager(@RequestBody Manager manager)
    {
        return service.registerManager(manager);
    }

    @PostMapping("/login")
    public ResponseEntity<Manager> login(@RequestParam String ssn)
    {
        Optional<Manager> manager = service.login(ssn);

        if (manager.isPresent())
        {
            return ResponseEntity.ok(manager.get());
        }

        return ResponseEntity.status(401).build();
    }
}
