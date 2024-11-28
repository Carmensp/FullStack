package com.cinexpress.videofriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.services.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("'/movie'")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("")
    public ResponseEntity<Void> storeMovie(@RequestBody Movie movie) {
        try {
            movieService.addMovie(movie);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("")
    public ResponseEntity<Void> updateMovie(@RequestBody Movie movie) {
        try {
            movieService.updateMovie(movie);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("/updateAvailability")
    public ResponseEntity<Void> updateAvailability(@RequestBody Movie movie) {
        try {
            movieService.updateAvailability(movie);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

     @DeleteMapping("")
    public ResponseEntity<Void> deleteMovie(@RequestParam(name = "movie_id") Long id) {
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
}
