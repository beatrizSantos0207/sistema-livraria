package com.livraria.livraria.service.storage;

import com.livraria.livraria.component.CopyComponent;
import com.livraria.livraria.entity.MusicalAlbums;
import com.livraria.livraria.exception.ItemEntityNotFoundException;
import com.livraria.livraria.model.dto.MusicalAlbumsDTO;
import com.livraria.livraria.repository.IMusicalAlbumsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicalAlbumsService {

    @Autowired
    private IMusicalAlbumsRepository musicalAlbumsRepository;

    @Autowired
    private CopyComponent copyComponent;


    public List<MusicalAlbumsDTO> findAll() {
        return musicalAlbumsRepository.findAll().stream()
                .map(musicalAlbuns -> copyComponent.copyEntityToDto(musicalAlbuns, MusicalAlbumsDTO.class))
                .collect(Collectors.toList());
    }

    public MusicalAlbumsDTO findById(Long id) {
        return musicalAlbumsRepository.findById(id)
                .map(musicalAlbuns -> copyComponent.copyEntityToDto(musicalAlbuns, MusicalAlbumsDTO.class))
                .orElseThrow(() -> new ItemEntityNotFoundException(MusicalAlbums.class));
    }

    public MusicalAlbumsDTO save(MusicalAlbumsDTO musicalAlbumsDTO) {
        MusicalAlbums entity = musicalAlbumsRepository.save(copyComponent.copyDtoItensToEntity(musicalAlbumsDTO, MusicalAlbums.class));
        return copyComponent.copyEntityToDto(entity, MusicalAlbumsDTO.class);
    }


    public MusicalAlbumsDTO update(Long id, MusicalAlbumsDTO musicalAlbumsDTO) {
       MusicalAlbumsDTO foundMusicAlbums = findById(id);
        if( id.equals(musicalAlbumsDTO.getId())){
            return save(musicalAlbumsDTO);
        }
        return null;
    }

    public void delete(Long id) {
        musicalAlbumsRepository.findById(id).ifPresent(item -> {
            musicalAlbumsRepository.deleteById(id);
        });
    }


}
