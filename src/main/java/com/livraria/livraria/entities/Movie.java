package com.livraria.livraria.entities;

import com.livraria.livraria.enuns.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_movies")
public class Movie extends Item implements Serializable {
    private static final Long serialVersionUID = 1L;

    private MovieGenre genre;
    private Studio studio;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
    private List<Director> directors = new java.util.ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
    private List<FilmMarker> filmMarkers = new java.util.ArrayList<>();

}


