package com.livraria.livraria.interfaces;

import sistemaLivraria.dto.Item;
import sistemaLivraria.enums.Categoria;

import java.util.List;

public interface IGerenciadorEstoque {
    int quantificarEstoquePorCategoria(Categoria categoria);

    List<Item> listarEstoque();

    List<Item> listarItensPorCategoria(Categoria categoria);
}
