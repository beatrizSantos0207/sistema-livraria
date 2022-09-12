package com.livraria.livraria.service.shopcart.mocks;

import com.livraria.livraria.model.dto.GameDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class GameDTOMock {

    public static GameDTO gimmeCodWarfare() {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(1L);
        gameDTO.setPrice(BigDecimal.valueOf(199.90));
        gameDTO.setQuantity(3L);
        gameDTO.setName("COD: Modern Warfare");

        return gameDTO;
    }

    public static GameDTO gimmeBorderlands3() {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(2L);
        gameDTO.setPrice(BigDecimal.valueOf(130));
        gameDTO.setQuantity(8L);
        gameDTO.setName("Borderlands 3");

        return gameDTO;
    }

    public static GameDTO gimmeCodWarfareQuantity1() {
        GameDTO gameDTO = gimmeCodWarfare();
        gameDTO.setQuantity(1L);
        return gameDTO;
    }

    public static GameDTO gimmeBorderlands3Quantity3() {
        GameDTO gameDTO = gimmeBorderlands3();
        gameDTO.setQuantity(3L);
        return gameDTO;
    }

    public static GameDTO gimmeCodWarfareQuantity4() {
        GameDTO gameDTO = gimmeCodWarfare();
        gameDTO.setQuantity(4L);
        return gameDTO;
    }

    public static List<GameDTO> gimmeShopCartGames() {
        return Arrays.asList(gimmeCodWarfareQuantity1(), gimmeBorderlands3Quantity3());
    }

    public static List<GameDTO> gimmeShopCartGamesTheShinningOverStock() {
        return Arrays.asList(gimmeCodWarfareQuantity4(), gimmeBorderlands3Quantity3());
    }

}
