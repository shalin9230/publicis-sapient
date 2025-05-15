package com.assignment.showtime_service.service;

import com.assignment.showtime_service.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service", path = "/api/movies", configuration = FeignClientConfig.class)
public interface MovieServiceClient {
    @GetMapping("/{id}/exists")
    Boolean validateMovieId(@PathVariable("id") Long id);
}
