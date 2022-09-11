package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.ToyDTO;
import com.livraria.livraria.service.storage.ToyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToyCartService {

    private ToyService toyService;

    public BigDecimal getAmount(List<ToyDTO> toys) {
        List<ToyDTO> toyDTOS = toys.stream()
                .filter(toyDTO -> {
                    return updateQuantityInStock(toyDTO) != null;
                })
                .collect(Collectors.toList());

        return getTotalAmountInToys(toyDTOS);
    }


    private ToyDTO updateQuantityInStock(ToyDTO toyDTO) {
        ToyDTO foundToy = toyService.findById(toyDTO.getId());
        if (toyDTO.getQuantity() < foundToy.getQuantity()) {
            foundToy.setQuantity(foundToy.getQuantity() - toyDTO.getQuantity());
        }
        return toyService.update(toyDTO.getId(), foundToy);
    }

    private BigDecimal getTotalAmountInToys(List<ToyDTO> toys) {
        BigDecimal totalAmountInToys = toys
                .stream()
                .map(toyDTO -> toyDTO.getPrice().multiply(BigDecimal.valueOf(toyDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInToys;
    }
}
