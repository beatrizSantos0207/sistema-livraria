package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.MovieDTO;
import com.livraria.livraria.service.storage.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieCartService {

    private MovieService movieService;

    public BigDecimal getAmount(List<MovieDTO> movies) {
        List<MovieDTO> movieDTOS = movies.stream()
                .filter(movieDTO -> {
                    return updateQuantityInStock(movieDTO) != null;
                })
                .collect(Collectors.toList());

        return getTotalAmountInMovies(movieDTOS);
    }


    private MovieDTO updateQuantityInStock(MovieDTO movieDTO) {
        MovieDTO foundMovie = movieService.findById(movieDTO.getId());
        if (movieDTO.getQuantity() < foundMovie.getQuantity()) {
            foundMovie.setQuantity(foundMovie.getQuantity() - movieDTO.getQuantity());
        }
        return movieService.update(movieDTO.getId(), foundMovie);
    }

    private BigDecimal getTotalAmountInMovies(List<MovieDTO> movies) {
        BigDecimal totalAmountInMovies = movies
                .stream()
                .map(movieDTO -> movieDTO.getPrice().multiply(BigDecimal.valueOf(movieDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInMovies;
    }
}
