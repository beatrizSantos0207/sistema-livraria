package com.livraria.livraria.service.shopcart.mocks;

import com.livraria.livraria.enums.PaymentType;
import com.livraria.livraria.model.ShopCart;
import com.livraria.livraria.model.dto.MusicalAlbumsDTO;

import java.math.BigDecimal;

import static com.livraria.livraria.service.shopcart.mocks.BookDTOMock.gimmeShopCartBooks;
import static com.livraria.livraria.service.shopcart.mocks.BookDTOMock.gimmeShopCartBooksOver200;
import static com.livraria.livraria.service.shopcart.mocks.MovieDTOMock.gimmeShopCartMovies;
import static com.livraria.livraria.service.shopcart.mocks.ToyDTOMock.gimmeShopCartToys;

public class ShopCartMock {

    public static ShopCart gimmeShopCartPix() {
        return  ShopCart.builder()
                .paymentType(PaymentType.PIX)
                .books(gimmeShopCartBooks())
                .toys(gimmeShopCartToys())
                .build();
    }

    public static ShopCart gimmeShopCartCredito() {
        return  ShopCart.builder()
                .paymentType(PaymentType.CREDITO)
                .books(gimmeShopCartBooksOver200())
                .movies(gimmeShopCartMovies())
                .build();
    }

}
