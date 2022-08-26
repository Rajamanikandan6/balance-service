package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceServiceController {
    @Autowired
    BalanceService balanceService;
    @GetMapping("/accounts/{accountId}/balances")
    public ResponseEntity<List<Balance>> deleteBalance(@PathVariable String accountId){
        List<Balance> balance = balanceService.getAllBalance(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }
}
