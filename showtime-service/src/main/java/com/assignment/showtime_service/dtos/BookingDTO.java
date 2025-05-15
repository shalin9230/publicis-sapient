package com.assignment.showtime_service.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long showtimeId;
    private Integer seatsBooked;

}
