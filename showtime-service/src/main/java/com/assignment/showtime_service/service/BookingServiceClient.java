package com.assignment.showtime_service.service;

import com.assignment.showtime_service.config.FeignClientConfig;
import com.assignment.showtime_service.dtos.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "booking-service", path = "/api/bookings", configuration = FeignClientConfig.class)
public interface BookingServiceClient {
    @GetMapping
    List<BookingDTO> getBookingsByShowtimeId(@RequestParam("showtimeId") Long showtimeId);
}
