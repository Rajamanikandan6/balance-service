package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BalanceServiceController {
    @Autowired
    BalanceService balanceService;
    @GetMapping("/accounts/{accountId}/balances")
    public ResponseEntity<List<BalanceDto>> getAllBalance(@PathVariable String accountId, @RequestParam int page , @RequestParam int pageSize){
        List<BalanceDto> balance = balanceService.getAllBalance(accountId,page,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }
}
