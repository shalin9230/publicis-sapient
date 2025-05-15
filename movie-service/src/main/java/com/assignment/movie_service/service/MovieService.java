package com.assignment.movie_service.service;

import com.assignment.movie_service.entity.Movie;
import com.assignment.movie_service.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepo;

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepo.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Boolean doesMovieExist(Long id) {
        return movieRepo.existsById(id);
    }

    public Movie getMovieByMovieName(String movieName) {
        return movieRepo.findMoviesByTitle(movieName);
    }
}
