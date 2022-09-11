package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.GameDTO;
import com.livraria.livraria.service.storage.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameCartService {

    private GameService gameService;

    public BigDecimal getAmount(List<GameDTO> games) {
        List<GameDTO> gameDTOS = games.stream()
                .filter(gameDTO -> {
                    return updateQuantityInStock(gameDTO) != null;
                })
                .collect(Collectors.toList());

        return getTotalAmountInGames(gameDTOS);
    }


    private GameDTO updateQuantityInStock(GameDTO gameDTO) {
        GameDTO foundGame = gameService.findById(gameDTO.getId());
        if (gameDTO.getQuantity() < foundGame.getQuantity()) {
            foundGame.setQuantity(foundGame.getQuantity() - gameDTO.getQuantity());
        }
        return gameService.update(gameDTO.getId(), foundGame);
    }

    private BigDecimal getTotalAmountInGames(List<GameDTO> games) {
        BigDecimal totalAmountInGames = games
                .stream()
                .map(gameDTO -> gameDTO.getPrice().multiply(BigDecimal.valueOf(gameDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInGames;
    }
}
