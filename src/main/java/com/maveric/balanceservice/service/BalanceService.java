package com.maveric.balanceservice.service;

import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    public int getBalance(int balanceId) {
        Optional<Balance> bal = balanceRepository.findById(balanceId);
        if (bal.isPresent()) {
            Balance AcctBal = bal.get();
            int resBalance = AcctBal.getAmount();

            return resBalance;
        }
        return 0;
    }
}
