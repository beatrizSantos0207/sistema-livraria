package com.livraria.livraria.entity;


import com.livraria.livraria.enums.GameGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    private String distributor;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;
}
