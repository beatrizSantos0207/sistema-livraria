package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.GameDTO;
import com.livraria.livraria.service.storage.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.livraria.livraria.service.shopcart.mocks.GameDTOMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameCartServiceTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameCartService gameCartService;

    @Test
    void given_list_of_games_when_getAmount_then_return_value_of_shopping() {
        List<GameDTO> shopCartGames = gimmeShopCartGames();
        when(gameService.findById(1L)).thenReturn(gimmeCodWarfare());
        when(gameService.findById(2L)).thenReturn(gimmeBorderlands3());
        BigDecimal amount = gameCartService.getAmount(shopCartGames);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(589.9).setScale(2));
    }

    @Test
    void given_list_of_games_when_getAmount_then_return_value_of_shopping_with_the_valid_amount() {
        List<GameDTO> shopCartGames = gimmeShopCartGamesTheShinningOverStock();
        when(gameService.findById(1L)).thenReturn(gimmeCodWarfare());
        when(gameService.findById(2L)).thenReturn(gimmeBorderlands3());
        BigDecimal amount = gameCartService.getAmount(shopCartGames);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(989.7).setScale(2));
    }
}