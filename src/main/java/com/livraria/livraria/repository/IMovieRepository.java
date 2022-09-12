package com.livraria.livraria.repository;

import com.livraria.livraria.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends ItemRepository<Movie> {
}
