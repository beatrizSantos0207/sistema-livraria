package com.livraria.livraria.model.dto;

import com.livraria.livraria.entity.Studio;
import com.livraria.livraria.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO extends ItemDTO {

    private MovieGenre genre;
    private StudioDTO studio;
    private List<DirectorDTO> directors = new java.util.ArrayList<>();
    private List<FilmMakerDTO> filmMarkers = new java.util.ArrayList<>();

}


