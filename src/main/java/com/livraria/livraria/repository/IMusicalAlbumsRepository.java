package com.livraria.livraria.repository;

import com.livraria.livraria.entity.MusicalAlbums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMusicalAlbumsRepository extends ItemRepository<MusicalAlbums> {
}
