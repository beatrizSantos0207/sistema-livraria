package com.livraria.livraria.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Builder
@Getter
public class LivrariaExceptionDTO {

	private final String mensagem;
	
	private final String mensagemDetalhada;
	
	public LivrariaExceptionDTO(String mensagem, String mensagemDetalhada) {
		this.mensagem = mensagem;
		this.mensagemDetalhada = Optional.ofNullable(mensagemDetalhada)
				.orElse(mensagem);
	}
	
}