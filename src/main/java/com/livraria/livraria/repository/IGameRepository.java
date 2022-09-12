package com.livraria.livraria.repository;

import com.livraria.livraria.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends ItemRepository<Game> {
}
