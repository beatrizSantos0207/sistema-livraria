package com.livraria.livraria.controller;

import com.livraria.livraria.model.dto.ToyDTO;
import com.livraria.livraria.service.storage.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toys")
public class ToyController {
    @Autowired
    private ToyService toyService;

    @GetMapping
    public ResponseEntity<List<ToyDTO>> findAll() {
        return ResponseEntity.ok(toyService.findAll());
    }

    @GetMapping("/{toyId}")
    public ResponseEntity<ToyDTO> findById(@PathVariable Long toyId) {
        return ResponseEntity.ok(toyService.findById(toyId));
    }

    @PostMapping
    public ResponseEntity<ToyDTO> save(@RequestBody ToyDTO toyDTO) {
        return ResponseEntity.ok(toyService.save(toyDTO));
    }

    @PutMapping("/{toyId}")
    public ResponseEntity<ToyDTO> update(@PathVariable Long toyId, @RequestBody ToyDTO toyDTO) {
        toyDTO.setId(toyId);
        return ResponseEntity.ok(toyService.save(toyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        toyService.delete(id);
        return ResponseEntity.noContent().build();

    }
}

