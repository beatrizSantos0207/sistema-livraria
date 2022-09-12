package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.BookDTO;
import com.livraria.livraria.service.storage.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.livraria.livraria.service.shopcart.mocks.BookDTOMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
class BookCartServiceTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookCartService bookCartService;

    @Test
    void given_list_of_books_when_getAmount_then_return_value_of_shopping() {
        List<BookDTO> shopCartBooks = gimmeShopCartBooks();
        when(bookService.findById(1L)).thenReturn(gimmeTheShinning());
        when(bookService.findById(2L)).thenReturn(gimmeTheCrow());
        BigDecimal amount = bookCartService.getAmount(shopCartBooks);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(121.50).setScale(2));
    }

    @Test
    void given_list_of_books_when_getAmount_then_return_value_of_shopping_with_the_valid_amount() {
        List<BookDTO> shopCartBooks = gimmeShopCartBooksTheShinningOverStock();
        when(bookService.findById(1L)).thenReturn(gimmeTheShinning());
        when(bookService.findById(2L)).thenReturn(gimmeTheCrow());
        BigDecimal amount = bookCartService.getAmount(shopCartBooks);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(140.40).setScale(2));
    }


    @Test
    void given_list_of_books_when_getAmount_then_return_value_of_shopping_with_discount() {
        List<BookDTO> shopCartBooks = gimmeShopCartBooksOver200();
        when(bookService.findById(1L)).thenReturn(gimmeTheShinning());
        when(bookService.findById(2L)).thenReturn(gimmeTheCrow());
        when(bookService.findById(3L)).thenReturn(gimmeTheHobbit());
        BigDecimal amount = bookCartService.getAmount(shopCartBooks);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(296.82));
    }

}