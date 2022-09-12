package com.livraria.livraria.service.shopcart.mocks;

import com.livraria.livraria.model.dto.ToyDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ToyDTOMock {

    public static ToyDTO gimmeChaveiro() {
        ToyDTO toyDTO = new ToyDTO();
        toyDTO.setId(1L);
        toyDTO.setPrice(BigDecimal.valueOf(15));
        toyDTO.setQuantity(3L);
        toyDTO.setName("Chaveiro Comemorativo");

        return toyDTO;
    }

    public static ToyDTO gimmeBola() {
        ToyDTO toyDTO = new ToyDTO();
        toyDTO.setId(2L);
        toyDTO.setPrice(BigDecimal.valueOf(33.37));
        toyDTO.setQuantity(8L);
        toyDTO.setName("Bola");

        return toyDTO;
    }

    public static ToyDTO gimmeChaveiroQuantity2() {
        ToyDTO toyDTO = gimmeChaveiro();
        toyDTO.setQuantity(2L);
        return toyDTO;
    }

    public static ToyDTO gimmeBolaQuantity3() {
        ToyDTO toyDTO = gimmeBola();
        toyDTO.setQuantity(3L);
        return toyDTO;
    }

    public static ToyDTO gimmeChaveiroQuantity4() {
        ToyDTO toyDTO = gimmeChaveiro();
        toyDTO.setQuantity(4L);
        return toyDTO;
    }

    public static List<ToyDTO> gimmeShopCartToys() {
        return Arrays.asList(gimmeChaveiroQuantity2(), gimmeBolaQuantity3());
    }

    public static List<ToyDTO> gimmeShopCartToysTheShinningOverStock() {
        return Arrays.asList(gimmeChaveiroQuantity4(), gimmeBolaQuantity3());
    }

}
