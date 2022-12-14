package com.livraria.livraria.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDTO {

	private String id;
	
	@NotNull
	private String name;

	@NotNull
	private String email;

	
}