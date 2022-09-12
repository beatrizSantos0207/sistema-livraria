package com.livraria.livraria.service.shopcart.mocks;

import com.livraria.livraria.entity.Cashier;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class CashierMock {

    public static Cashier gimmeCashierId1() {
        return Cashier.builder()
                .id(1L)
                .qtIncoming(BigDecimal.ZERO)
                .qtOutFlow(BigDecimal.ZERO)
                .qtTotal(BigDecimal.ZERO)
                .build();
    }

    public static List<Cashier> gimmeCashierId1List() {
        return Collections.singletonList(gimmeCashierId1());
    }

}
