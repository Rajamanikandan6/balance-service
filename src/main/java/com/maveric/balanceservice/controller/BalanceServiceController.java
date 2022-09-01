package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class BalanceServiceController {
    @Autowired
    BalanceService balanceService;

    @GetMapping("/accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<Object> getBalanceDetails(@PathVariable String accountId , @PathVariable String balanceId) {
        String bal = balanceService.getBalance(balanceId,accountId);
        return ResponseEntity.status(HttpStatus.OK).body(bal);
    }

}
