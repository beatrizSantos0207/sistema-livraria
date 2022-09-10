package com.livraria.livraria.repository;

import com.livraria.livraria.entity.Item;
import com.livraria.livraria.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository<T extends Item> extends JpaRepository<T, Long> {
}
