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

    public Balance updateBalance(Balance balance,String balanceId){
        Optional<Balance> balanceFromDb = balanceRepository.findById(balanceId);
            if(balanceFromDb.isPresent()) {
            Balance newBal = balanceFromDb.get();
            newBal.setAccountId(balance.getAccountId());
            newBal.setCurrency(balance.getCurrency());
            newBal.setAmount(balance.getAmount());


            return balanceRepository.save(newBal);
        }
            return null;
    }
}
