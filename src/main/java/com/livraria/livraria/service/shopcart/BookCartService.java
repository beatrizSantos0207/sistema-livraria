package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.dto.BookDTO;
import com.livraria.livraria.service.storage.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCartService {

    public static final BigDecimal BOOK_DESCOUNT_OVER_200 = BigDecimal.valueOf(0.15);

    private BookService bookService;

    public BigDecimal getAmount(List<BookDTO> books) {
        List<BookDTO> bookDTOS = books.stream()
                .filter(bookDTO -> {
                    return updateQuantityInStock(bookDTO) != null;
                })
                .collect(Collectors.toList());

        return getProfit(bookDTOS);
    }


    private BookDTO updateQuantityInStock(BookDTO bookDTO) {
        BookDTO foundBook = bookService.findById(bookDTO.getId());
        if (bookDTO.getQuantity() < foundBook.getQuantity()) {
            foundBook.setQuantity(foundBook.getQuantity() - bookDTO.getQuantity());
        }
        return bookService.update(bookDTO.getId(), foundBook);
    }

    private BigDecimal getProfit(List<BookDTO> books) {
        BigDecimal totalAmountInBooks = getTotalAmountInBooks(books);
        return totalAmountInBooks.subtract(getDescount(totalAmountInBooks));
    }

    private BigDecimal getDescount(BigDecimal totalAmountInBooks) {
        if (totalAmountInBooks.compareTo(BigDecimal.valueOf(200)) >= 1) {
            return totalAmountInBooks.multiply(BOOK_DESCOUNT_OVER_200);
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal getTotalAmountInBooks(List<BookDTO> books) {
        BigDecimal totalAmountInBooks = books
                .stream()
                .map(book -> book.getPrice().multiply(BigDecimal.valueOf(book.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmountInBooks;
    }

}
