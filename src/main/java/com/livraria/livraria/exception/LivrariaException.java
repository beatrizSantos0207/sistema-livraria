package com.livraria.livraria.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class LivrariaException extends RuntimeException {

	private static final long serialVersionUID = 1694126086950366275L;
	
	@Getter
	private final HttpStatus httpStatus;
	
	public abstract LivrariaExceptionDTO getExceptionDTO();
	
	protected LivrariaException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}