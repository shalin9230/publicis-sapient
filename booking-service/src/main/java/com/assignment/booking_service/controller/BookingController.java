package com.assignment.booking_service.controller;

import com.assignment.booking_service.entiry.Booking;
import com.assignment.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking bookTickets(@RequestBody Booking booking) {
        Long userId = booking.getUserId();
        Long showtimeId = booking.getShowtimeId();
        Integer numberOfSeats = booking.getSeatsBooked();

        return bookingService.bookTickets(userId, showtimeId, numberOfSeats);
    }

    @GetMapping("/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId){
        return bookingService.getBookingsByUserId(userId);
    }

    @GetMapping
    public List<Booking> getBookingByShowtimeId(@RequestParam("showtimeId") Long showtimeId){
        return bookingService.getBookingByShowtimeId(showtimeId);
    }
}
