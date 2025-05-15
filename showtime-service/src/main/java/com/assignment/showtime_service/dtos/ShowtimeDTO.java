package com.assignment.showtime_service.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeDTO {
    private Long id;
    private Long movieId;
    private LocalDateTime showTime;
    private Integer availableSeats;
    private List<BookingDTO> bookings;
}
