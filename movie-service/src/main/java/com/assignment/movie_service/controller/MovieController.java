package com.assignment.movie_service.controller;

import com.assignment.movie_service.entity.Movie;
import com.assignment.movie_service.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/{id}/exists")
    public Boolean validateMovie(@PathVariable Long id) {
        return movieService.doesMovieExist(id);
    }

    @GetMapping("/movieName/{movieName}")
    public Movie getMovieByMovieName(@PathVariable String movieName) {
        return movieService.getMovieByMovieName(movieName);
    }
}
