package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.MovieDTO;
import com.livraria.livraria.model.dto.MusicalAlbumsDTO;
import com.livraria.livraria.model.dto.MusicalAlbumsDTO;
import com.livraria.livraria.service.storage.MusicalAlbumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicalAlbumsCartService {

    private final MusicalAlbumsService musicalAlbumService;

    private final List<MusicalAlbumsDTO> foundAlbuns = new ArrayList<>();

    public BigDecimal getAmount(List<MusicalAlbumsDTO> musicalAlbums) {
        List<MusicalAlbumsDTO> musicalAlbumDTOS = musicalAlbums.stream()
                .map(musicalAlbumDTO -> {
                    return verifyQuantityInStock(musicalAlbumDTO);
                })
                .collect(Collectors.toList());
        updateStock();
        return getTotalAmountInMusicalAlbumss(musicalAlbumDTOS).setScale(2);
    }


    private MusicalAlbumsDTO verifyQuantityInStock(MusicalAlbumsDTO musicalAlbumsDTO) {
        MusicalAlbumsDTO foundMusicalAlbums = musicalAlbumService.findById(musicalAlbumsDTO.getId());
        if (musicalAlbumsDTO.getQuantity() > foundMusicalAlbums.getQuantity()) {
            musicalAlbumsDTO.setQuantity(foundMusicalAlbums.getQuantity());
        }
        musicalAlbumsDTO.setPrice(foundMusicalAlbums.getPrice());
        foundMusicalAlbums.setQuantity(foundMusicalAlbums.getQuantity() - musicalAlbumsDTO.getQuantity());
        foundAlbuns.add(foundMusicalAlbums);
        return musicalAlbumsDTO;
    }

    private BigDecimal getTotalAmountInMusicalAlbumss(List<MusicalAlbumsDTO> musicalAlbums) {
        BigDecimal totalAmountInMusicalAlbumss = musicalAlbums
                .stream()
                .map(musicalAlbumDTO -> musicalAlbumDTO.getPrice().multiply(BigDecimal.valueOf(musicalAlbumDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInMusicalAlbumss;
    }

    private void updateStock() {
        foundAlbuns.stream().forEach(albunsDTO -> musicalAlbumService.update(albunsDTO.getId(), albunsDTO));
    }
}
