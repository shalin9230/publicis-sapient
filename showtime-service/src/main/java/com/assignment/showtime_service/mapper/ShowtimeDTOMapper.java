package com.assignment.showtime_service.mapper;

import com.assignment.showtime_service.dtos.ShowtimeDTO;
import com.assignment.showtime_service.entity.Showtime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Function;

@Service
public class ShowtimeDTOMapper implements Function<Showtime, ShowtimeDTO> {
    @Override
    public ShowtimeDTO apply(Showtime showtime) {
        return new ShowtimeDTO(
                showtime.getId(),
                showtime.getMovieId(),
                showtime.getShowTime(),
                showtime.getAvailableSeats(),
                new ArrayList<>()
        );
    }
}
