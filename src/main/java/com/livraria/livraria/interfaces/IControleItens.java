package com.livraria.livraria.interfaces;

import sistemaLivraria.dto.Item;
import sistemaLivraria.enums.Categoria;

public interface IControleItens {
    Item mostrarItem(String id);

    void adicionarProduto(Item novoItem);

    <T> void alterarItem(int index, T itemAlterado);

    void removerItem(String id);

    Categoria getCategoriaOfItem(Item item);
}
