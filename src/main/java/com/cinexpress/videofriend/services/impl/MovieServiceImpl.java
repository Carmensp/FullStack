package com.cinexpress.videofriend.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.repository.MovieRepository;
import com.cinexpress.videofriend.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

 @Autowired
    MovieRepository movieRepository;

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(Movie datos) {
        Optional<Movie> optionalEntity = movieRepository.findById(datos.getId());
        if(!optionalEntity.isEmpty()){
            Movie movie = optionalEntity.get();
            movie.setTitle(datos.getTitle());
            movie.setFormat(datos.getFormat());
            movie.setGenero(datos.getGenero());
            movie.setLanguage(datos.getLanguage());
            movie.setAvailability(datos.getAvailability());
            movie.setYear(datos.getYear());
            movie.setActors(datos.getActors());
            movie.setDirectors(datos.getDirectors());
           movieRepository.save(movie);
        }
    }

    @Override
    public void updateAvailability(Movie datos) {
        Optional<Movie> optionalEntity = movieRepository.findById(datos.getId());
        if(!optionalEntity.isEmpty()){
            Movie movie = optionalEntity.get();
            movie.setAvailability(datos.getAvailability());
           movieRepository.save(movie);
        }
    }
    

    @Override
    public void deleteMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(!movie.isEmpty()){
            movieRepository.delete(movie.get());
        } 
    }
    
}
