package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.ToyDTO;
import com.livraria.livraria.model.dto.ToyDTO;
import com.livraria.livraria.service.storage.ToyService;
import com.livraria.livraria.service.storage.ToyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToyCartService {

    private final ToyService toyService;

    private final List<ToyDTO> foundToys = new ArrayList<>();


    public BigDecimal getAmount(List<ToyDTO> toys) {
        List<ToyDTO> toyDTOS = toys.stream()
                .filter(toyDTO -> {
                    return verifyQuantityInStock(toyDTO) != null;
                })
                .collect(Collectors.toList());
        updateStock();
        return getTotalAmountInToys(toyDTOS).setScale(2);
    }


    private ToyDTO verifyQuantityInStock(ToyDTO toyDTO) {
        ToyDTO foundToy = toyService.findById(toyDTO.getId());
        if (toyDTO.getQuantity() > foundToy.getQuantity()) {
            toyDTO.setQuantity(foundToy.getQuantity());
        }
        toyDTO.setPrice(foundToy.getPrice());
        foundToy.setQuantity(foundToy.getQuantity() - toyDTO.getQuantity());
        foundToys.add(foundToy);
        return toyDTO;
    }

    private BigDecimal getTotalAmountInToys(List<ToyDTO> toys) {
        BigDecimal totalAmountInToys = toys
                .stream()
                .map(toyDTO -> toyDTO.getPrice().multiply(BigDecimal.valueOf(toyDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInToys;
    }

    private void updateStock() {
        foundToys.stream().forEach(toyDTO -> toyService.update(toyDTO.getId(), toyDTO));
    }

}
