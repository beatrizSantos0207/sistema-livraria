package com.livraria.livraria.service.shopcart.mocks;

import com.livraria.livraria.model.dto.MovieDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MovieDTOMock {

    public static MovieDTO gimmeAvengers() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setPrice(BigDecimal.valueOf(19.90));
        movieDTO.setQuantity(3L);
        movieDTO.setName("The Avengers");

        return movieDTO;
    }

    public static MovieDTO gimmeJusticeLeague() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(2L);
        movieDTO.setPrice(BigDecimal.valueOf(13.90));
        movieDTO.setQuantity(8L);
        movieDTO.setName("Justice league");

        return movieDTO;
    }

    public static MovieDTO gimmeCodWarfareQuantity1() {
        MovieDTO movieDTO = gimmeAvengers();
        movieDTO.setQuantity(1L);
        return movieDTO;
    }

    public static MovieDTO gimmeJusticeLeagueQuantity3() {
        MovieDTO movieDTO = gimmeJusticeLeague();
        movieDTO.setQuantity(3L);
        return movieDTO;
    }

    public static MovieDTO gimmeAvengersQuantity4() {
        MovieDTO movieDTO = gimmeAvengers();
        movieDTO.setQuantity(4L);
        return movieDTO;
    }

    public static List<MovieDTO> gimmeShopCartMovies() {
        return Arrays.asList(gimmeCodWarfareQuantity1(), gimmeJusticeLeagueQuantity3());
    }

    public static List<MovieDTO> gimmeShopCartMoviesTheShinningOverStock() {
        return Arrays.asList(gimmeAvengersQuantity4(), gimmeJusticeLeagueQuantity3());
    }

}
