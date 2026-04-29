package com.hotel.hotelapp.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
import com.hotel.hotelapp.entity.Manager;
import com.hotel.hotelapp.repository.ManagerRepository;

@Service
public class ManagerService
{
    private final ManagerRepository repo;

    public ManagerService(ManagerRepository repo)
    {
        this.repo = repo;
    }

    public Manager registerManager(Manager manager)
    {
        return repo.save(manager);
    }

    public Optional<Manager> login(String ssn)
    {
        return repo.findById(ssn);
    }
}
