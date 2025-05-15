package com.assignment.showtime_service.repository;

import com.assignment.showtime_service.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
