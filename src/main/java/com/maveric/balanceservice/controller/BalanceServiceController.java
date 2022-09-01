package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BalanceServiceController {

    @Autowired
    BalanceService balanceService;

    @PostMapping("/accounts/{accountId}/balances")
    public ResponseEntity<BalanceDto> createBalance(@Valid @RequestBody Balance balance, @PathVariable String accountId) {
        BalanceDto balanceDetails = balanceService.createBalance(balance);
        return ResponseEntity.status(HttpStatus.CREATED).body(balanceDetails);
    }

}
