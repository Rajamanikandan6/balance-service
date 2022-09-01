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

    @PutMapping("/accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<BalanceDto> updateBalance(@Valid @RequestBody Balance balance, @PathVariable String accountId, @PathVariable String balanceId) {
        balance.setAccountId(accountId);
        BalanceDto balanceDetails = balanceService.updateBalance(balance,balanceId);
        return ResponseEntity.status(HttpStatus.OK).body(balanceDetails);
    }

}
