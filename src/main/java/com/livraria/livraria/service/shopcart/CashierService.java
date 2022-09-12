package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.entity.Cashier;
import com.livraria.livraria.enums.PaymentType;
import com.livraria.livraria.exception.CashierNotWorkingException;
import com.livraria.livraria.model.ShopCart;
import com.livraria.livraria.model.TransactionFeedback;
import com.livraria.livraria.repository.ICashierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CashierService {

    private final ICashierRepository cashierRepository;
    private final BookCartService bookCartService;
    private final MovieCartService movieCartService;
    private final MusicalAlbumsCartService musicalAlbumsCartService;
    private final GameCartService gameCartService;
    private final ToyCartService toyCartService;

    public TransactionFeedback executeTransaction(ShopCart shopCart) {
        Cashier cashier = cashierRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new CashierNotWorkingException());
        cashier.setQtIncoming(getDiscountByPaymentType(shopCart));
        cashier.setUpdatedAt(LocalDateTime.now());
        cashierRepository.save(cashier);

        return TransactionFeedback.builder()
                .message("SUCESSO")
                .transaction(getDiscountByPaymentType(shopCart))
                .updatedAt(LocalDateTime.now())
                .build();
    }


    private BigDecimal getDiscountByPaymentType(ShopCart shopCart) {
        if (!PaymentType.CREDITO.equals(shopCart.getPaymentType())) {
            return getShopCartTotal(shopCart)
                    .subtract(getDiscount(shopCart));
        }
        return getShopCartTotal(shopCart);
    }

    private BigDecimal getDiscount(ShopCart shopCart) {
        return getShopCartTotal(shopCart).multiply(BigDecimal.valueOf(0.15));
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
        return isEmpty(shopCart.getBooks()) ? BigDecimal.ZERO :
                bookCartService.getAmount(shopCart.getBooks());
    }

    private BigDecimal getValueOfMovies(ShopCart shopCart) {
        return isEmpty(shopCart.getMovies()) ? BigDecimal.ZERO :
                movieCartService.getAmount(shopCart.getMovies());
    }

    private BigDecimal getValueOfGames(ShopCart shopCart) {
        return isEmpty(shopCart.getGames()) ? BigDecimal.ZERO :
                gameCartService.getAmount(shopCart.getGames());
    }

    private BigDecimal getValueOfMusicalAlbums(ShopCart shopCart) {
        return isEmpty(shopCart.getAlbuns()) ? BigDecimal.ZERO :
                musicalAlbumsCartService.getAmount(shopCart.getAlbuns());
    }

    private BigDecimal getValueOfToys(ShopCart shopCart) {
        return isEmpty(shopCart.getToys()) ? BigDecimal.ZERO :
                toyCartService.getAmount(shopCart.getToys());
    }

    private <T> Boolean isEmpty(List<T> list){
        return list == null || list.isEmpty();
    }

}
