package com.hotel.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapp.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, String>
{

}
