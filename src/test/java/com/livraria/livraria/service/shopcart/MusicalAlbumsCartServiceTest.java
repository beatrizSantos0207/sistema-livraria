package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.MusicalAlbumsDTO;
import com.livraria.livraria.service.storage.MusicalAlbumsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.livraria.livraria.service.shopcart.mocks.MusicalAlbumsDTOMock.*;
import static com.livraria.livraria.service.shopcart.mocks.MusicalAlbumsDTOMock.gimmeArcadiumStadium;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MusicalAlbumsCartServiceTest {

    @Mock
    private MusicalAlbumsService musicalAlbumsService;

    @InjectMocks
    private MusicalAlbumsCartService musicalAlbumsCartService;

    @Test
    void given_list_of_musicalAlbumss_when_getAmount_then_return_value_of_shopping() {
        List<MusicalAlbumsDTO> shopCartMusicalAlbumss = gimmeShopCartMusicalAlbums();
        when(musicalAlbumsService.findById(1L)).thenReturn(gimmeZoioDeLula());
        when(musicalAlbumsService.findById(2L)).thenReturn(gimmeArcadiumStadium());
        BigDecimal amount = musicalAlbumsCartService.getAmount(shopCartMusicalAlbumss);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(61.6).setScale(2));
    }

    @Test
    void given_list_of_musicalAlbumss_when_getAmount_then_return_value_of_shopping_with_the_valid_amount() {
        List<MusicalAlbumsDTO> shopCartMusicalAlbumss = gimmeShopCartMusicalAlbumssTheShinningOverStock();
        when(musicalAlbumsService.findById(1L)).thenReturn(gimmeZoioDeLula());
        when(musicalAlbumsService.findById(2L)).thenReturn(gimmeArcadiumStadium());
        BigDecimal amount = musicalAlbumsCartService.getAmount(shopCartMusicalAlbumss);

        assertThat(amount).isEqualTo(BigDecimal.valueOf(101.4).setScale(2));
    }
}