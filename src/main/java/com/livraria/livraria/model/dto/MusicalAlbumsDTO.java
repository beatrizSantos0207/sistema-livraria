package com.livraria.livraria.model.dto;

import com.livraria.livraria.enums.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicalAlbumsDTO extends ItemDTO {

    private MusicGenre genre;
    private String stamp;
    private String musician;


}
