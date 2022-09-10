package com.livraria.livraria.security.service;

import com.livraria.livraria.model.User;
import com.livraria.livraria.repository.UserRepository;
import com.livraria.livraria.security.jwt.JwtProvider;
import com.livraria.livraria.security.model.dto.LoginDTO;
import com.livraria.livraria.security.model.dto.TokenDTO;
import com.livraria.livraria.security.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	private static final String BEARER = "Bearer";

	public LoginDTO logar(UserDTO userDTO) throws LoginException {
		User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new LoginException(userDTO.getEmail()));
		
		if (encoder.matches(userDTO.getPassword(), user.getPassword())) {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
			String token = jwtProvider.obtainToken(userDetails);
			
			return LoginDTO.builder()
					.id(user.getId())
					.token(token)
					.name(user.getName())
					.email(user.getEmail())
					.build();
		} else {
			throw new LoginException(userDTO.getEmail());
		}
	}

	public TokenDTO refresh(String token) {
		String refreshedToken = jwtProvider.refreshToken(token.split(BEARER)[1].trim());
		return new TokenDTO(refreshedToken);
	}

}