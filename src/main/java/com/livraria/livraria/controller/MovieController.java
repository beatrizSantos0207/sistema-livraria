package com.livraria.livraria.controller;

import com.livraria.livraria.model.dto.MovieDTO;
import com.livraria.livraria.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.findById(movieId));
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.save(movieDTO));
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long movieId, @RequestBody MovieDTO movieDTO) {
        movieDTO.setId(movieId);
        return ResponseEntity.ok(movieService.save(movieDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();

    }
}

