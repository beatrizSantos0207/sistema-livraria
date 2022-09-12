package com.livraria.livraria.repository;

import com.livraria.livraria.entity.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IToyRepository extends ItemRepository<Toy> {
}
