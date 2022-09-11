package com.livraria.livraria.service.storage;

import com.livraria.livraria.component.CopyComponent;
import com.livraria.livraria.entity.Book;
import com.livraria.livraria.entity.Cashier;
import com.livraria.livraria.exception.ItemEntityNotFoundException;
import com.livraria.livraria.model.dto.BookDTO;
import com.livraria.livraria.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private CopyComponent copyComponent;

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> copyComponent.copyEntityToDto(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    public BookDTO findById(Long id) {
        return bookRepository.findById(id)
                .map(book -> copyComponent.copyEntityToDto(book, BookDTO.class))
                .orElseThrow(() -> new ItemEntityNotFoundException(Book.class));
    }

    public BookDTO save(BookDTO bookDTO) {
        Book entity = bookRepository.save(copyComponent.copyDtoItensToEntity(bookDTO, Book.class));
        return copyComponent.copyEntityToDto(entity, BookDTO.class);
    }

    public BookDTO update(Long id, BookDTO bookDTO) {
        BookDTO foundBook = findById(id);
        if (id.equals(bookDTO.getId())) {
            return save(bookDTO);
        }
        return null;
    }

    public void delete(Long id) {
        bookRepository.findById(id).ifPresent(item -> {
            bookRepository.deleteById(id);
        });
    }


}
