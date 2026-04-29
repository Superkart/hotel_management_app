package com.hotel.hotelapp.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import com.hotel.hotelapp.entity.Booking;
import com.hotel.hotelapp.entity.Client;
import com.hotel.hotelapp.entity.Hotel;
import com.hotel.hotelapp.entity.Room;
import com.hotel.hotelapp.exception.BookingConflictException;
import com.hotel.hotelapp.repository.BookingRepository;
import com.hotel.hotelapp.repository.RoomRepository;
import org.springframework.data.domain.Pageable;

@Service
public class BookingService
{
    private final BookingRepository repo;
    private final RoomRepository roomRepo;

    public BookingService(BookingRepository repo, RoomRepository roomRepo)
    {
        this.repo = repo;
        this.roomRepo = roomRepo;
    }

    public Booking createBooking(Booking booking)
    {
        Room room = roomRepo.findById(booking.getRoom().getRoomNumber()).orElseThrow();
        booking.setRoom(room);

        List<Booking> conflicts = repo.findOverlappingBookings(
            room.getRoomNumber(),
            room.getHotel().getHotelId(),
            booking.getStartDate(),
            booking.getEndDate()
        );

        if (!conflicts.isEmpty())
        {
            throw new BookingConflictException("Room is already booked for this date range");
        }

        return repo.save(booking);
    }

    public List<Booking> getAllBookings()
    {
        return repo.findAll();
    }

    public List<Booking> getMyBookings(String email)
    {
        return repo.findByClientEmail(email);
    }

    public List<Object[]> getTopClients(int k)
    {
        Pageable pageable = PageRequest.of(0, k);
        return repo.findTopClients(pageable);
    }

    public List<Object[]> getRoomBookingCounts()
    {
        return repo.getRoomBookingCounts();
    }

    public List<Object[]> getClientSpending()
    {
        return repo.getClientSpending();
    }

    public Object autoBook(Long hotelId, LocalDate startDate, LocalDate endDate, String email, double price)
    {
        List<Room> available = repo.findAvailableRoomsInHotel(hotelId, startDate, endDate);

        if (!available.isEmpty())
        {
            Room room = available.get(0);
            Booking booking = new Booking();
            booking.setRoom(room);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            booking.setPricePerDay(price);
            Client client = new Client();
            client.setEmail(email);
            booking.setClient(client);
            return repo.save(booking);
        }

        List<Hotel> alternatives = repo.findAlternativeHotels(hotelId, startDate, endDate);
        return alternatives;
    }

    public long countBookingsByClientAndHotel(String email, Long hotelId)
    {
        return repo.countBookingsByClientAndHotel(email, hotelId);
    }
}
