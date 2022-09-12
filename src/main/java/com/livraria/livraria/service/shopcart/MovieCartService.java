package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.MovieDTO;
import com.livraria.livraria.model.dto.MovieDTO;
import com.livraria.livraria.service.storage.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieCartService {

    private final MovieService movieService;

    private final List<MovieDTO> foundMovies = new ArrayList<>();


    public BigDecimal getAmount(List<MovieDTO> movies) {
        List<MovieDTO> movieDTOS = movies.stream()
                .filter(movieDTO -> {
                    return verifyQuantityInStock(movieDTO) != null;
                })
                .collect(Collectors.toList());
        updateStock();
        return getTotalAmountInMovies(movieDTOS).setScale(2);
    }


    private MovieDTO verifyQuantityInStock(MovieDTO movieDTO) {
        MovieDTO foundMovie = movieService.findById(movieDTO.getId());
        if (movieDTO.getQuantity() > foundMovie.getQuantity()) {
            movieDTO.setQuantity(foundMovie.getQuantity());
        }
        movieDTO.setPrice(foundMovie.getPrice());
        foundMovie.setQuantity(foundMovie.getQuantity() - movieDTO.getQuantity());
        foundMovies.add(foundMovie);
        return movieDTO;
    }

    private BigDecimal getTotalAmountInMovies(List<MovieDTO> movies) {
        BigDecimal totalAmountInMovies = movies
                .stream()
                .map(movieDTO -> movieDTO.getPrice().multiply(BigDecimal.valueOf(movieDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInMovies;
    }

    private void updateStock() {
        foundMovies.stream().forEach(movieDTO -> movieService.update(movieDTO.getId(), movieDTO));
    }

}
