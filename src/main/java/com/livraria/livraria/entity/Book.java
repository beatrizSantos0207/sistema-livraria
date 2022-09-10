package com.livraria.livraria.entity;

import com.livraria.livraria.enums.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_books")
public class Book extends Item {

    private BookGenre genre;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublishingCompany publisher;

    @ManyToMany
    @JoinTable(name = "tb_author_book", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new java.util.ArrayList<>();


}
