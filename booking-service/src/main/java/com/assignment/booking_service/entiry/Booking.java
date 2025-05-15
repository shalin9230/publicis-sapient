package com.assignment.booking_service.entiry;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long showtimeId;

    @Column(nullable = false)
    private Integer seatsBooked;
    @Column
    private String paymentId;

    @Column
    private String paymentStatus;
}
