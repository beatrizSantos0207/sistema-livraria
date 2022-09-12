package com.livraria.livraria.model;

import com.livraria.livraria.entity.Item;
import com.livraria.livraria.enums.PaymentType;
import com.livraria.livraria.model.dto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopCart {

    PaymentType paymentType;

    private List<BookDTO> books = new ArrayList<>();
    private List<GameDTO> games = new ArrayList<>();
    private List<MovieDTO> movies = new ArrayList<>();
    private List<MusicalAlbumsDTO> albuns = new ArrayList<>();
    private List<ToyDTO> toys = new ArrayList<>();


}
