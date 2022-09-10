package com.livraria.livraria.entities;

import com.livraria.livraria.enuns.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_books")
public class Book extends Item implements Serializable {

    private List<PublishingCompany> publishers;
    private BookGenre genre;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    private List<Author> authors = new java.util.ArrayList<>();



}
