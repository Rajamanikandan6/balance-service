package com.maveric.balanceservice.service;

import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    public String getBalance(String balanceId,String accountId) {
        Optional<Balance> bal = balanceRepository.findByAccountIdAndBalanceId(balanceId,accountId);
        if (bal.isPresent()) {
            Balance acctBalance = bal.get();
            return acctBalance.getAmount();
        }
        else{
            throw new BalanceNotFoundException(balanceId);
        }
    }
}
