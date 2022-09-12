package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.BookDTO;
import com.livraria.livraria.model.dto.GameDTO;
import com.livraria.livraria.model.dto.GameDTO;
import com.livraria.livraria.service.storage.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameCartService {

    private final GameService gameService;

    private final List<GameDTO> foundGames = new ArrayList<>();


    public BigDecimal getAmount(List<GameDTO> games) {
        List<GameDTO> gameDTOS = games.stream()
                .map(gameDTO -> {
                    return verifyQuantityInStock(gameDTO);
                })
                .collect(Collectors.toList());

        updateStock();
        return getTotalAmountInGames(gameDTOS).setScale(2);
    }

    private GameDTO verifyQuantityInStock(GameDTO gameDTO) {
        GameDTO foundGame = gameService.findById(gameDTO.getId());
        if (gameDTO.getQuantity() > foundGame.getQuantity()) {
            gameDTO.setQuantity(foundGame.getQuantity());
        }
        gameDTO.setPrice(foundGame.getPrice());
        foundGame.setQuantity(foundGame.getQuantity() - gameDTO.getQuantity());
        foundGames.add(foundGame);
        return gameDTO;
    }


    private BigDecimal getTotalAmountInGames(List<GameDTO> games) {
        BigDecimal totalAmountInGames = games
                .stream()
                .map(gameDTO -> gameDTO.getPrice().multiply(BigDecimal.valueOf(gameDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInGames;
    }

    private void updateStock(){
        foundGames.stream().forEach(gameDTO -> gameService.update(gameDTO.getId(), gameDTO));
    }

}
