package com.livraria.livraria.interfaces;

import sistemaLivraria.dto.Item;

import java.util.List;

public interface ICaixa {
    double getCaixa();
    void realizarCompra(List<Item> itens);
}
