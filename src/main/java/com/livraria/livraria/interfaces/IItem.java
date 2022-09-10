package com.livraria.livraria.interfaces;

import com.livraria.livraria.entities.Item;

public interface IItem {
     String getNome();
     void setNome(String nome);
     String getId();
     double getPreco();
     void setPreco(double preco);

     <T extends Item> void atualizarItem(T item);

}
