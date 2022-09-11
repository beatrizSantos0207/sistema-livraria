package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.entity.Cashier;
import com.livraria.livraria.enums.PaymentType;
import com.livraria.livraria.exception.CashierNotWorkingException;
import com.livraria.livraria.model.ShopCart;
import com.livraria.livraria.model.dto.BookDTO;
import com.livraria.livraria.repository.ICashierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CashierService {


    private ICashierRepository cashierRepository;
    private BookCartService bookCartService;
    private MovieCartService movieCartService;
    private MusicalAlbumsCartService musicalAlbumsCartService;
    private GameCartService gameCartService;
    private ToyCartService toyCartService;


    public void realizarCompra(ShopCart shopCart) {
        Cashier cashier = cashierRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new CashierNotWorkingException());
        cashier.setQtIncoming(getDescoutByPaymentType(shopCart));
        cashierRepository.save(cashier);
    }


    private BigDecimal getDescoutByPaymentType(ShopCart shopCart) {
        if (!PaymentType.CREDITO.equals(shopCart.getPaymentType())) {
            return getShopCartTotal(shopCart).multiply(BigDecimal.valueOf(0.15));
        }
        return BigDecimal.ZERO;
    }


    private BigDecimal getShopCartTotal(ShopCart shopCart) {
        return Stream.of(getValueOfBooks(shopCart),
                        getValueOfMovies(shopCart),
                        getValueOfGames(shopCart),
                        getValueOfMusicalAlbums(shopCart),
                        getValueOfToys(shopCart))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private BigDecimal getValueOfBooks(ShopCart shopCart) {
        return shopCart.getBooks().isEmpty() ? BigDecimal.ZERO :
                bookCartService.getAmount(shopCart.getBooks());
    }

    private BigDecimal getValueOfMovies(ShopCart shopCart) {
        return shopCart.getMovies().isEmpty() ? BigDecimal.ZERO :
                movieCartService.getAmount(shopCart.getMovies());
    }

    private BigDecimal getValueOfGames(ShopCart shopCart) {
        return shopCart.getGames().isEmpty() ? BigDecimal.ZERO :
                gameCartService.getAmount(shopCart.getGames());
    }

    private BigDecimal getValueOfMusicalAlbums(ShopCart shopCart) {
        return shopCart.getAlbuns().isEmpty() ? BigDecimal.ZERO :
                musicalAlbumsCartService.getAmount(shopCart.getAlbuns());
    }

    private BigDecimal getValueOfToys(ShopCart shopCart) {
        return shopCart.getToys().isEmpty() ? BigDecimal.ZERO :
                toyCartService.getAmount(shopCart.getToys());
    }
}
