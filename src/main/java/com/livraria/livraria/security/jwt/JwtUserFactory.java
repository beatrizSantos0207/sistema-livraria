package com.livraria.livraria.security.jwt;

import com.livraria.livraria.model.User;
import com.livraria.livraria.security.model.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class JwtUserFactory {

	public JwtUser build(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), mapToGrantedAuthorities(), true);
	}

	private List<GrantedAuthority> mapToGrantedAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

}
