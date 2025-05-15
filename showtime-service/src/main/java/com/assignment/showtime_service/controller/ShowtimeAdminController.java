package com.assignment.showtime_service.controller;

import com.assignment.showtime_service.entity.Showtime;
import com.assignment.showtime_service.service.ShowtimeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/showtimes/admin")
public class ShowtimeAdminController {
    @Autowired
    private ShowtimeAdminService showtimeAdminService;

    @PostMapping
    public Showtime createShowtime(@RequestBody Showtime showtime) {
        return showtimeAdminService.createShowtime(showtime);
    }
}
