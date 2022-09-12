package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.MovieDTO;
import com.livraria.livraria.service.storage.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.livraria.livraria.service.shopcart.mocks.MovieDTOMock.*;
import static com.livraria.livraria.service.shopcart.mocks.MovieDTOMock.gimmeJusticeLeague;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieCartServiceTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieCartService movieCartService;

    @Test
    void given_list_of_movies_when_getAmount_then_return_value_of_shopping() {
        List<MovieDTO> shopCartMovies = gimmeShopCartMovies();
        when(movieService.findById(1L)).thenReturn(gimmeAvengers());
        when(movieService.findById(2L)).thenReturn(gimmeJusticeLeague());
        BigDecimal amount = movieCartService.getAmount(shopCartMovies);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(61.6).setScale(2));
    }

    @Test
    void given_list_of_movies_when_getAmount_then_return_value_of_shopping_with_the_valid_amount() {
        List<MovieDTO> shopCartMovies = gimmeShopCartMoviesTheShinningOverStock();
        when(movieService.findById(1L)).thenReturn(gimmeAvengers());
        when(movieService.findById(2L)).thenReturn(gimmeJusticeLeague());
        BigDecimal amount = movieCartService.getAmount(shopCartMovies);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(101.4).setScale(2));
    }
}