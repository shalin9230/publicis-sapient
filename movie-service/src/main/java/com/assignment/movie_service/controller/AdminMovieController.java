package com.assignment.movie_service.controller;

import com.assignment.movie_service.entity.Movie;
import com.assignment.movie_service.service.AdminMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies/admin")
public class AdminMovieController {

    @Autowired
    private AdminMovieService adminService;

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie newMovie = adminService.createMovie(movie);

        return ResponseEntity.ok(newMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        Movie updatedMovie = adminService.updateMovie(id, movie);

        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        adminService.deleteMovie(id);

        return ResponseEntity.noContent().build();
    }
}
