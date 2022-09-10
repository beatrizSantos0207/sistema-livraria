package com.livraria.livraria.enums;

public enum Rotulo {

    ADULT_LABEL("destinado ao público adulto");

   public final String label;
   private Rotulo(String label) {
        this.label = label;
    }
}
