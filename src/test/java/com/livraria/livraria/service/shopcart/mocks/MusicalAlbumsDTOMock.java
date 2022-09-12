package com.livraria.livraria.service.shopcart.mocks;

import com.livraria.livraria.model.dto.MusicalAlbumsDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MusicalAlbumsDTOMock {

    public static MusicalAlbumsDTO gimmeZoioDeLula() {
        MusicalAlbumsDTO musicalAlbumsDTO = new MusicalAlbumsDTO();
        musicalAlbumsDTO.setId(1L);
        musicalAlbumsDTO.setPrice(BigDecimal.valueOf(19.90));
        musicalAlbumsDTO.setQuantity(3L);
        musicalAlbumsDTO.setName("Zoio de Lula");

        return musicalAlbumsDTO;
    }

    public static MusicalAlbumsDTO gimmeArcadiumStadium() {
        MusicalAlbumsDTO musicalAlbumsDTO = new MusicalAlbumsDTO();
        musicalAlbumsDTO.setId(2L);
        musicalAlbumsDTO.setPrice(BigDecimal.valueOf(13.90));
        musicalAlbumsDTO.setQuantity(8L);
        musicalAlbumsDTO.setName("Arcadium Stadium");

        return musicalAlbumsDTO;
    }

    public static MusicalAlbumsDTO gimmeZoioDeLulaQuantity1() {
        MusicalAlbumsDTO musicalAlbumsDTO = gimmeZoioDeLula();
        musicalAlbumsDTO.setQuantity(1L);
        return musicalAlbumsDTO;
    }

    public static MusicalAlbumsDTO gimmeArcadiumStadiumQuantity3() {
        MusicalAlbumsDTO musicalAlbumsDTO = gimmeArcadiumStadium();
        musicalAlbumsDTO.setQuantity(3L);
        return musicalAlbumsDTO;
    }

    public static MusicalAlbumsDTO gimmeZoioDeLulaQuantity4() {
        MusicalAlbumsDTO musicalAlbumsDTO = gimmeZoioDeLula();
        musicalAlbumsDTO.setQuantity(4L);
        return musicalAlbumsDTO;
    }

    public static List<MusicalAlbumsDTO> gimmeShopCartMusicalAlbums() {
        return Arrays.asList(gimmeZoioDeLulaQuantity1(), gimmeArcadiumStadiumQuantity3());
    }

    public static List<MusicalAlbumsDTO> gimmeShopCartMusicalAlbumssTheShinningOverStock() {
        return Arrays.asList(gimmeZoioDeLulaQuantity4(), gimmeArcadiumStadiumQuantity3());
    }

}
