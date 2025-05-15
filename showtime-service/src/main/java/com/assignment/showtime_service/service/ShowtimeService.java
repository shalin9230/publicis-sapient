package com.assignment.showtime_service.service;

import com.assignment.showtime_service.dtos.BookingDTO;
import com.assignment.showtime_service.dtos.ShowtimeDTO;
import com.assignment.showtime_service.entity.Showtime;
import com.assignment.showtime_service.mapper.ShowtimeDTOMapper;
import com.assignment.showtime_service.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepo;

    @Autowired
    private BookingServiceClient bookingClient;

    @Autowired
    private ShowtimeDTOMapper showtimeDTOMapper;

    public List<Showtime> getAllShowtimes() {
        return showtimeRepo.findAll();
    }

    public ShowtimeDTO getShowtimeWithBookings(Long id) {
        Showtime showtime = showtimeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        List<BookingDTO> bookings = bookingClient.getBookingsByShowtimeId(id);

        ShowtimeDTO showtimeDTO = showtimeDTOMapper.apply(showtime);
        showtimeDTO.setBookings(bookings);

        return showtimeDTO;
    }

    public Showtime bookSeats(Long showtimeId, Integer seats) {
        Showtime showtime = showtimeRepo.findById(showtimeId).orElseThrow(() -> new RuntimeException("Showtime not found"));

        if (showtime.getAvailableSeats() < seats) {
            throw new RuntimeException("Not enough seats available");
        }

        showtime.setAvailableSeats(showtime.getAvailableSeats() - seats);

        return showtimeRepo.save(showtime);
    }

    public Boolean doesShowtimeExist(Long id) {
        return showtimeRepo.existsById(id);
    }

}
