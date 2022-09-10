package com.livraria.livraria.entities;


import com.livraria.livraria.enuns.GameGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_games")
public class Game extends Item implements Serializable {
    private static final Long serialVersionUID = 1L;

    private GameGenre genre;
    private Studio studio;
    private String distributor;


}
