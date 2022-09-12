package com.livraria.livraria.service.shopcart.mocks;

import com.livraria.livraria.model.dto.BookDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BookDTOMock {

    public static BookDTO gimmeTheShinning() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setPrice(BigDecimal.valueOf(18.90));
        bookDTO.setQuantity(3L);
        bookDTO.setName("The Shinning");

        return bookDTO;
    }

    public static BookDTO gimmeTheCrow() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(2L);
        bookDTO.setPrice(BigDecimal.valueOf(27.90));
        bookDTO.setQuantity(8L);
        bookDTO.setName("The Crow");

        return bookDTO;
    }

    public static BookDTO gimmeTheHobbit() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(3L);
        bookDTO.setPrice(BigDecimal.valueOf(75.90));
        bookDTO.setQuantity(3L);
        bookDTO.setName("The Hobbit");

        return bookDTO;
    }

    public static BookDTO gimmeTheShinningQuantity2() {
        BookDTO bookDTO = gimmeTheShinning();
        bookDTO.setQuantity(2L);
        return bookDTO;
    }

    public static BookDTO gimmeTheCrowQuantity3() {
        BookDTO bookDTO = gimmeTheCrow();
        bookDTO.setQuantity(3L);
        return bookDTO;
    }

    public static BookDTO gimmeTheShinningQuantity4() {
        BookDTO bookDTO = gimmeTheShinning();
        bookDTO.setQuantity(4L);
        return bookDTO;
    }

    public static List<BookDTO> gimmeShopCartBooks() {
        return Arrays.asList(gimmeTheShinningQuantity2(), gimmeTheCrowQuantity3());
    }

    public static List<BookDTO> gimmeShopCartBooksOver200() {
        return Arrays.asList(gimmeTheShinningQuantity2(), gimmeTheCrowQuantity3(), gimmeTheHobbit());
    }

    public static List<BookDTO> gimmeShopCartBooksTheShinningOverStock() {
        return Arrays.asList(gimmeTheShinningQuantity4(), gimmeTheCrowQuantity3());
    }

}
