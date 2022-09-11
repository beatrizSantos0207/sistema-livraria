package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.MusicalAlbumsDTO;
import com.livraria.livraria.service.storage.MusicalAlbumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicalAlbumsCartService {

    private MusicalAlbumsService musicalAlbumService;

    public BigDecimal getAmount(List<MusicalAlbumsDTO> musicalAlbums) {
        List<MusicalAlbumsDTO> musicalAlbumDTOS = musicalAlbums.stream()
                .filter(musicalAlbumDTO -> {
                    return updateQuantityInStock(musicalAlbumDTO) != null;
                })
                .collect(Collectors.toList());

        return getTotalAmountInMusicalAlbumss(musicalAlbumDTOS);
    }


    private MusicalAlbumsDTO updateQuantityInStock(MusicalAlbumsDTO musicalAlbumDTO) {
        MusicalAlbumsDTO foundMusicalAlbums = musicalAlbumService.findById(musicalAlbumDTO.getId());
        if (musicalAlbumDTO.getQuantity() < foundMusicalAlbums.getQuantity()) {
            foundMusicalAlbums.setQuantity(foundMusicalAlbums.getQuantity() - musicalAlbumDTO.getQuantity());
        }
        return musicalAlbumService.update(musicalAlbumDTO.getId(), foundMusicalAlbums);
    }

    private BigDecimal getTotalAmountInMusicalAlbumss(List<MusicalAlbumsDTO> musicalAlbums) {
        BigDecimal totalAmountInMusicalAlbumss = musicalAlbums
                .stream()
                .map(musicalAlbumDTO -> musicalAlbumDTO.getPrice().multiply(BigDecimal.valueOf(musicalAlbumDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInMusicalAlbumss;
    }
}
