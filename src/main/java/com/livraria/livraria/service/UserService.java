package com.livraria.livraria.service;

import com.livraria.livraria.component.CopyComponent;
import com.livraria.livraria.exception.EmailUserAlreadyTakenException;
import com.livraria.livraria.model.User;
import com.livraria.livraria.model.dto.UserPostDTO;
import com.livraria.livraria.repository.UserRepository;
import com.livraria.livraria.security.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.livraria.livraria.exception.EntityNotFoundException;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CopyComponent copyComponent;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final String DEFAULT_PASSWORD = "password";
	
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream()
				.map(u -> copyComponent.copyEntityToDto(u, UserDTO.class))
				.collect(Collectors.toList());
	}
	
	public UserDTO findById(String userId) {
		return userRepository.findById(userId)
				.map(u -> copyComponent.copyEntityToDto(u, UserDTO.class))
				.orElseThrow(() -> new EntityNotFoundException(User.class));
	}
	
	public UserDTO save(UserPostDTO userPostDTO) {
		
		User user = copyComponent.copyDtoToEntity(userPostDTO, User.class);
		user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
		
		if (user.getId() == null && userRepository.existsByEmail(user.getEmail())) {
			throw new EmailUserAlreadyTakenException(user.getEmail());
		}
		
		User savedUser = userRepository.save(user);
		
		return copyComponent.copyEntityToDto(savedUser, UserDTO.class);
	}
	
	public User getCurrentUser() {
		return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName().toLowerCase())
				.orElseThrow(() -> new EntityNotFoundException(User.class));
	}

	@Transactional
	public void delete(String id) {
		userRepository.findById(id).map(u -> {
			userRepository.delete(u);
			return u;
		}).orElseThrow(() -> new EntityNotFoundException(User.class));
	}
	
}