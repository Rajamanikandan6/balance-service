package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.feignclient.AccountFeignService;
import com.maveric.balanceservice.model.Account;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BalanceServiceController {

    @Autowired
    BalanceService balanceService;

    @Autowired
    AccountFeignService accountFeignService;

    @GetMapping("/accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<Object> getBalanceDetails(@PathVariable String accountId , @PathVariable String balanceId,@RequestHeader(value = "userId") String userId) {
        List<Account> account = null;
        ResponseEntity<List<Account>> accountList = accountFeignService.getAccounts(userId);
        account = accountList.getBody();
        balanceService.findAccountIdBelongsToCurrentUser(account,accountId);
        String bal = balanceService.getBalance(balanceId,accountId);
        return ResponseEntity.status(HttpStatus.OK).body(bal);
    }
    @GetMapping("/accounts/{accountId}/balances")
    public ResponseEntity<List<BalanceDto>> getAllBalance(@PathVariable String accountId, @RequestParam int page , @RequestParam int pageSize,@RequestHeader(value = "userId") String userId){
        List<BalanceDto> balance = balanceService.getAllBalance(accountId,page,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }

    @DeleteMapping("/accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<Object> deleteBalance(@PathVariable String balanceId ,@PathVariable String accountId,@RequestHeader(value = "userId") String userId){
        String desc = balanceService.deleteBalance(balanceId,accountId);
        return ResponseEntity.status(HttpStatus.OK).body(desc);
    }

    @PutMapping("/accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<BalanceDto> updateBalance(@Valid @RequestBody Balance balance, @PathVariable String accountId, @PathVariable String balanceId,@RequestHeader(value = "userId") String userId) {
        balance.setAccountId(accountId);
        BalanceDto balanceDetails = balanceService.updateBalance(balance,balanceId);
        return ResponseEntity.status(HttpStatus.OK).body(balanceDetails);
    }
    @PostMapping("/accounts/{accountId}/balances")
    public ResponseEntity<BalanceDto> createBalance(@Valid @RequestBody Balance balance, @PathVariable String accountId,@RequestHeader(value = "userId") String userId) {
        BalanceDto balanceDetails = balanceService.createBalance(balance);
        return ResponseEntity.status(HttpStatus.CREATED).body(balanceDetails);
    }
}
