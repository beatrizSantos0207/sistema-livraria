package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.BookDTO;
import com.livraria.livraria.service.storage.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCartService {

    public static final BigDecimal BOOK_DISCOUNT_OVER_200 = BigDecimal.valueOf(0.15);

    private final BookService bookService;

    private final List<BookDTO> foundBooks = new ArrayList<>();

    public BigDecimal getAmount(List<BookDTO> books) {
        List<BookDTO> bookDTOS = books.stream()
                .map(bookDTO -> {
                    return verifyQuantityInStock(bookDTO);
                })
                .collect(Collectors.toList());

        updateStock();
        return getProfit(bookDTOS).setScale(2);
    }


    private BookDTO verifyQuantityInStock(BookDTO bookDTO) {
        BookDTO foundBook = bookService.findById(bookDTO.getId());
        if (bookDTO.getQuantity() > foundBook.getQuantity()) {
            bookDTO.setQuantity(foundBook.getQuantity());
        }
        bookDTO.setPrice(foundBook.getPrice());
        foundBook.setQuantity(foundBook.getQuantity() - bookDTO.getQuantity());
        foundBooks.add(foundBook);
        return bookDTO;
    }

    private BigDecimal getProfit(List<BookDTO> books) {
        BigDecimal totalAmountInBooks = getTotalAmountInBooks(books);
        return totalAmountInBooks.subtract(getDiscount(totalAmountInBooks));
    }

    private BigDecimal getDiscount(BigDecimal totalAmountInBooks) {
        if (totalAmountInBooks.compareTo(BigDecimal.valueOf(200)) >= 1) {
            return totalAmountInBooks.multiply(BOOK_DISCOUNT_OVER_200);
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal getTotalAmountInBooks(List<BookDTO> books) {
        return books
                .stream()
                .map(book -> book.getPrice().multiply(BigDecimal.valueOf(book.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void updateStock() {
        foundBooks.stream().forEach(bookDTO -> bookService.update(bookDTO.getId(), bookDTO));
    }
}
