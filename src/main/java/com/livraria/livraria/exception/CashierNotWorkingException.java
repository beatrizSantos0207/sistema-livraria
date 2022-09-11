package com.livraria.livraria.exception;

import com.livraria.livraria.model.BaseEntity;
import org.springframework.http.HttpStatus;

public class CashierNotWorkingException extends LivrariaException {

    private static final long serialVersionUID = -7842969292588503168L;

    public CashierNotWorkingException() {
        super(HttpStatus.BAD_REQUEST);
    }

    @Override
    public LivrariaExceptionDTO getExceptionDTO() {
        return LivrariaExceptionDTO.builder()
                .mensagem("O caixa está apresentando defeito, favor se existe algum caixa inicializado!")
                .build();
    }

}
