package com.livraria.livraria.model.dto;


import com.livraria.livraria.entity.Studio;
import com.livraria.livraria.enums.GameGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GameDTO extends ItemDTO {

    private GameGenre genre;
    private StudioDTO studio;
    private String distributor;


}
