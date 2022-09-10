package com.livraria.livraria.service;

import com.livraria.livraria.component.CopyComponent;
import com.livraria.livraria.entity.Toy;
import com.livraria.livraria.exception.ItemEntityNotFoundException;
import com.livraria.livraria.model.dto.ToyDTO;
import com.livraria.livraria.repository.IToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToyService {

    @Autowired
    private IToyRepository toyRepository;

    @Autowired
    private CopyComponent copyComponent;


    public List<ToyDTO> findAll() {
        return toyRepository.findAll().stream()
                .map(toy -> copyComponent.copyEntityToDto(toy, ToyDTO.class))
                .collect(Collectors.toList());
    }

    public ToyDTO findById(Long id) {
        return toyRepository.findById(id)
                .map(toy -> copyComponent.copyEntityToDto(toy, ToyDTO.class))
                .orElseThrow(() -> new ItemEntityNotFoundException(Toy.class));
    }

    public ToyDTO save(ToyDTO toyDTO) {
        Toy entity = toyRepository.save(copyComponent.copyDtoItensToEntity(toyDTO, Toy.class));
        return copyComponent.copyEntityToDto(entity, ToyDTO.class);
    }

    public ToyDTO update(Long id, ToyDTO toyDTO) {
        ToyDTO foundToy = findById(id);
        if( id.equals(toyDTO.getId())){
            return save(toyDTO);
        }
        return null;
    }

    public void delete(Long id) {
        toyRepository.findById(id).ifPresent(item -> {
            toyRepository.deleteById(id);
        });
    }


}
