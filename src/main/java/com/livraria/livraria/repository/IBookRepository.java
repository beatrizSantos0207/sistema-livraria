package com.livraria.livraria.repository;

import com.livraria.livraria.entity.Book;
import com.livraria.livraria.entity.Item;
import com.livraria.livraria.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends ItemRepository<Book> {
}
