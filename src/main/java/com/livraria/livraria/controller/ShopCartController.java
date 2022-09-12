package com.livraria.livraria.controller;

import com.livraria.livraria.model.ShopCart;
import com.livraria.livraria.model.TransactionFeedback;
import com.livraria.livraria.model.dto.MovieDTO;
import com.livraria.livraria.service.shopcart.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopcart")
public class ShopCartController {

    @Autowired
    private CashierService cashierService;

    @PostMapping
    public ResponseEntity<TransactionFeedback> executeTransaction(@RequestBody ShopCart shopCart) {
        return ResponseEntity.ok(cashierService.executeTransaction(shopCart));
    }

}
