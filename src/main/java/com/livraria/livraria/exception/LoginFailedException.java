package com.livraria.livraria.exception;

import org.springframework.http.HttpStatus;

public class LoginFailedException extends LivrariaException {

	private static final long serialVersionUID = -1099853919014279213L;

	public LoginFailedException() {
		super(HttpStatus.UNAUTHORIZED);
	}

	@Override
	public LivrariaExceptionDTO getExceptionDTO() {
		return LivrariaExceptionDTO.builder()
				.mensagem("Verifique os dados informados!")
				.build();
	}

}