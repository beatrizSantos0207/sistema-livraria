package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.ToyDTO;
import com.livraria.livraria.service.storage.ToyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.livraria.livraria.service.shopcart.mocks.ToyDTOMock.*;
import static com.livraria.livraria.service.shopcart.mocks.ToyDTOMock.gimmeBola;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToyCartServiceTest {

    @Mock
    private ToyService toyService;

    @InjectMocks
    private ToyCartService toyCartService;

    @Test
    void given_list_of_toys_when_getAmount_then_return_value_of_shopping() {
        List<ToyDTO> shopCartToys = gimmeShopCartToys();
        when(toyService.findById(1L)).thenReturn(gimmeChaveiro());
        when(toyService.findById(2L)).thenReturn(gimmeBola());
        BigDecimal amount = toyCartService.getAmount(shopCartToys);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(130.11).setScale(2));
    }

    @Test
    void given_list_of_toys_when_getAmount_then_return_value_of_shopping_with_the_valid_amount() {
        List<ToyDTO> shopCartToys = gimmeShopCartToysTheShinningOverStock();
        when(toyService.findById(1L)).thenReturn(gimmeChaveiro());
        when(toyService.findById(2L)).thenReturn(gimmeBola());
        BigDecimal amount = toyCartService.getAmount(shopCartToys);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(145.11).setScale(2));
    }
}