package com.livraria.livraria.repository;

import com.livraria.livraria.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICashierRepository extends JpaRepository<Cashier, Long> {
}
