package com.livraria.livraria.service;

import com.livraria.livraria.component.CopyComponent;
import com.livraria.livraria.entity.Movie;
import com.livraria.livraria.exception.ItemEntityNotFoundException;
import com.livraria.livraria.model.dto.MovieDTO;
import com.livraria.livraria.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private CopyComponent copyComponent;


    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream()
                .map(movie -> copyComponent.copyEntityToDto(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    public MovieDTO findById(Long id) {
        return movieRepository.findById(id)
                .map(movie -> copyComponent.copyEntityToDto(movie, MovieDTO.class))
                .orElseThrow(() -> new ItemEntityNotFoundException(Movie.class));
    }

    public MovieDTO save(MovieDTO movieDTO) {
        Movie entity = movieRepository.save(copyComponent.copyDtoItensToEntity(movieDTO, Movie.class));
        return copyComponent.copyEntityToDto(entity, MovieDTO.class);
    }

    public MovieDTO update(Long id, MovieDTO movieDTO) {
        MovieDTO foundMovie = findById(id);
        if( id.equals(movieDTO.getId())){
            return save(movieDTO);
        }
        return null;
    }

    public void delete(Long id) {
        movieRepository.findById(id).ifPresent(item -> {
            movieRepository.deleteById(id);
        });
    }

}
