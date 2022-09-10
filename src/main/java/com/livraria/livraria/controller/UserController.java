package com.livraria.livraria.controller;

import com.livraria.livraria.model.dto.UserPostDTO;
import com.livraria.livraria.security.model.dto.UserDTO;
import com.livraria.livraria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> findById(@PathVariable String userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody UserPostDTO userPostDTO) {
		return ResponseEntity.ok(userService.save(userPostDTO));
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> update(@PathVariable String usuarioId, @RequestBody UserPostDTO userPostDTO) {
		userPostDTO.setId(usuarioId);
		return ResponseEntity.ok(userService.save(userPostDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
}