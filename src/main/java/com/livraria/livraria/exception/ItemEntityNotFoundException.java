package com.livraria.livraria.exception;

import com.livraria.livraria.entity.Item;
import org.springframework.http.HttpStatus;

public class ItemEntityNotFoundException extends LivrariaException {

    private static final long serialVersionUID = -7842969292588503168L;

    private final Class<? extends Item> clazz;

    public ItemEntityNotFoundException(Class<? extends Item> clazz) {
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