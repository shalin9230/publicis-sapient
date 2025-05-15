package com.assignment.movie_service.repository;

import com.assignment.movie_service.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
