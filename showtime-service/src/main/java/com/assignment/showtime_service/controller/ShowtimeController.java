package com.assignment.showtime_service.controller;

import com.assignment.showtime_service.dtos.ShowtimeDTO;
import com.assignment.showtime_service.entity.Showtime;
import com.assignment.showtime_service.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping
    public List<Showtime> fetchAllShowtimes(){
        return showtimeService.getAllShowtimes();
    }

    @GetMapping("/{id}")
    public ShowtimeDTO getShowtimeWithBookings(@PathVariable Long id) {
        return showtimeService.getShowtimeWithBookings(id);
    }

    @PutMapping("/{id}/book-seats/{seats}")
    public void bookSeats(@PathVariable("id") Long showtimeId, @PathVariable("seats") Integer seats) {
        showtimeService.bookSeats(showtimeId, seats);
    }

    @GetMapping("/{id}/exists")
    public Boolean validateShowtime(@PathVariable("id") Long id) {
        return showtimeService.doesShowtimeExist(id);
    }
}
