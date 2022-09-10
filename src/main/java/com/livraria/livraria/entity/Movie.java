package com.livraria.livraria.entity;

import com.livraria.livraria.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToMany
    @JoinTable(name = "tb_movie_director", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private List<Director> directors = new java.util.ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_movie_filmmaker", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "filmmaker_id"))
    private List<FilmMarker> filmMarkers = new java.util.ArrayList<>();

}


