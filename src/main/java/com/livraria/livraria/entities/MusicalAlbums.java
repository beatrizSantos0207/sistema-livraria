package com.livraria.livraria.entities;

import com.livraria.livraria.enuns.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_musicalAlbuns")
public class MusicalAlbums extends Item implements Serializable {
    private static final Long serialVersionUID = 1L;

    private MusicGenre genre;
    private List<String> stamps;
    private List<String> musicians;


}
