package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceServiceController {
    @Autowired
    BalanceService balanceService;
    @DeleteMapping("/accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<Object> deleteBalance(@PathVariable String balanceId){
        String desc = balanceService.deleteBalance(balanceId);
        return ResponseEntity.status(HttpStatus.OK).body(desc);
    }
}
