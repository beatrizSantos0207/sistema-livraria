package com.livraria.livraria.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	private String id;
	
	private String token;
	
	private String name;
	
	private String email;
	
}
