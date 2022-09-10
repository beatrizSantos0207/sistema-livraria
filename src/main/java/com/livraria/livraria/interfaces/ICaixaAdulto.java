package com.livraria.livraria.interfaces;

import sistemaLivraria.dto.Consumidor;
import sistemaLivraria.dto.Item;

import java.util.List;

public interface ICaixaAdulto {

    double getCaixa();

    void realizarCompra(List<Item> itens, Consumidor consumidor);

}
