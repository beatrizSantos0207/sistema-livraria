package com.livraria.livraria.service.shopcart;

import com.livraria.livraria.model.ShopCart;
import com.livraria.livraria.model.TransactionFeedback;
import com.livraria.livraria.repository.ICashierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.livraria.livraria.service.shopcart.mocks.CashierMock.gimmeCashierId1List;
import static com.livraria.livraria.service.shopcart.mocks.ShopCartMock.gimmeShopCartCredito;
import static com.livraria.livraria.service.shopcart.mocks.ShopCartMock.gimmeShopCartPix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CashierServiceTest {

    @Mock
    private ICashierRepository cashierRepository;
    @Mock
    private BookCartService bookCartService;
    @Mock
    private MovieCartService movieCartService;
    @Mock
    private MusicalAlbumsCartService musicalAlbumsCartService;
    @Mock
    private GameCartService gameCartService;
    @Mock
    private ToyCartService toyCartService;

    @InjectMocks
    private CashierService cashierService;

    @Test
    void given_shopcart_wht_payment_method_not_credit_then_return_value_with_discount() {
        ShopCart shopCart = gimmeShopCartPix();
        when(cashierRepository.findAll()).thenReturn(gimmeCashierId1List());
        when(bookCartService.getAmount(shopCart.getBooks())).thenReturn(BigDecimal.valueOf(121.50).setScale(2));
        when(toyCartService.getAmount(shopCart.getToys())).thenReturn(BigDecimal.valueOf(130.11).setScale(2));

        TransactionFeedback transactionFeedback = cashierService.executeTransaction(shopCart);

        assertThat(transactionFeedback.getTransaction()
                .setScale(2, RoundingMode.HALF_UP))
                .isEqualTo(BigDecimal.valueOf(213.87));
    }

    @Test
    void given_shopcart_wht_payment_method_credit_then_return_value_without_discount() {
        ShopCart shopCart = gimmeShopCartCredito();
        when(cashierRepository.findAll()).thenReturn(gimmeCashierId1List());
        when(bookCartService.getAmount(shopCart.getBooks())).thenReturn(BigDecimal.valueOf(140.40).setScale(2));
        when(movieCartService.getAmount(shopCart.getMovies())).thenReturn(BigDecimal.valueOf(61.6).setScale(2));

        TransactionFeedback transactionFeedback = cashierService.executeTransaction(shopCart);

        assertThat(transactionFeedback.getTransaction())
                .isEqualTo(BigDecimal.valueOf(202).setScale(2));
    }

}