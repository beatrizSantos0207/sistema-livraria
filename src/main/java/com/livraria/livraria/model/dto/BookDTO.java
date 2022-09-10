package com.livraria.livraria.model.dto;

import com.livraria.livraria.enums.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO extends ItemDTO {

    private PublishingCompanyDTO publisher;
    private BookGenre genre;
    private List<AuthorDTO> authors = new java.util.ArrayList<>();

}
