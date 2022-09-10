package com.livraria.livraria.controller;

import com.livraria.livraria.model.dto.GameDTO;
import com.livraria.livraria.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameDTO>> findAll() {
        return ResponseEntity.ok(gameService.findAll());
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDTO> findById(@PathVariable Long gameId) {
        return ResponseEntity.ok(gameService.findById(gameId));
    }

    @PostMapping
    public ResponseEntity<GameDTO> save(@RequestBody GameDTO gameDTO) {
        return ResponseEntity.ok(gameService.save(gameDTO));
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<GameDTO> update(@PathVariable Long gameId, @RequestBody GameDTO gameDTO) {
        gameDTO.setId(gameId);
        return ResponseEntity.ok(gameService.save(gameDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();

    }
}

