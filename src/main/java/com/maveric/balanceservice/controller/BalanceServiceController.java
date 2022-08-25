package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceServiceController {

    @Autowired
    BalanceService balanceService;

    @PutMapping("/accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<Balance> createBalance(@RequestBody Balance balance, @PathVariable String accountId, @PathVariable int balanceId) {
        balance.setAccountId(accountId);
        Balance balanceDetails = balanceService.updateBalance(balance,balanceId);
        return ResponseEntity.status(HttpStatus.CREATED).body(balanceDetails);
    }

}
