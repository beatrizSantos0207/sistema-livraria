package com.livraria.livraria.controller;

import com.livraria.livraria.model.dto.MusicalAlbumsDTO;
import com.livraria.livraria.service.MusicalAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/albuns")
public class MusicalAlbumsController {
    @Autowired
    private MusicalAlbumsService musicalAbumsService;

    @GetMapping
    public ResponseEntity<List<MusicalAlbumsDTO>> findAll() {
        return ResponseEntity.ok(musicalAbumsService.findAll());
    }

    @GetMapping("/{musicalAlbumsId}")
    public ResponseEntity<MusicalAlbumsDTO> findById(@PathVariable Long musicalAlbumsId) {
        return ResponseEntity.ok(musicalAbumsService.findById(musicalAlbumsId));
    }

    @PostMapping
    public ResponseEntity<MusicalAlbumsDTO> save(@RequestBody MusicalAlbumsDTO musicalAlbumsDTO) {
        return ResponseEntity.ok(musicalAbumsService.save(musicalAlbumsDTO));
    }

    @PutMapping("/{musicalAlbumsId}")
    public ResponseEntity<MusicalAlbumsDTO> update(@PathVariable Long musicalAlbumsId, @RequestBody MusicalAlbumsDTO musicalAlbumsDTO) {
        musicalAlbumsDTO.setId(musicalAlbumsId);
        return ResponseEntity.ok(musicalAbumsService.save(musicalAlbumsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        musicalAbumsService.delete(id);
        return ResponseEntity.noContent().build();

    }
}

