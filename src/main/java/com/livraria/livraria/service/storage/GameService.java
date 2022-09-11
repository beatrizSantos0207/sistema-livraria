package com.livraria.livraria.service.storage;

import com.livraria.livraria.component.CopyComponent;
import com.livraria.livraria.entity.Game;
import com.livraria.livraria.exception.ItemEntityNotFoundException;
import com.livraria.livraria.model.dto.GameDTO;
import com.livraria.livraria.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private CopyComponent copyComponent;


    public List<GameDTO> findAll() {
        return gameRepository.findAll().stream()
                .map(game -> copyComponent.copyEntityToDto(game, GameDTO.class))
                .collect(Collectors.toList());
    }

    public GameDTO findById(Long id) {
        return gameRepository.findById(id)
                .map(game -> copyComponent.copyEntityToDto(game, GameDTO.class))
                .orElseThrow(() -> new ItemEntityNotFoundException(Game.class));
    }

    public GameDTO save(GameDTO gameDTO) {
        Game entity = gameRepository.save(copyComponent.copyDtoItensToEntity(gameDTO, Game.class));
        return copyComponent.copyEntityToDto(entity, GameDTO.class);
    }

    public GameDTO update(Long id, GameDTO gameDTO) {
        GameDTO foundGame = findById(id);
        if( id.equals(gameDTO.getId())){
            return save(gameDTO);
        }
        return null;
    }

    public void delete(Long id) {
        gameRepository.findById(id).ifPresent(item -> {
            gameRepository.deleteById(id);
        });
    }


}
