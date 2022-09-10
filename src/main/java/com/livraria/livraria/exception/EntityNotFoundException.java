package com.livraria.livraria.exception;

import com.livraria.livraria.model.BaseEntity;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends LivrariaException {

	private static final long serialVersionUID = -7842969292588503168L;
	
	private final Class<? extends BaseEntity> clazz;
	
	public EntityNotFoundException(Class<? extends BaseEntity> clazz) {
		super(HttpStatus.BAD_REQUEST);
		this.clazz = clazz;
	}

	@Override
	public LivrariaExceptionDTO getExceptionDTO() {
		return LivrariaExceptionDTO.builder()
				.mensagem("Não existe " + clazz.getSimpleName() + " para o id informado")
				.build();
	}

}