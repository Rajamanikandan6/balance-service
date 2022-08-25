package com.maveric.balanceservice.service;

import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    public Balance createBalance(Balance balance){
       return balanceRepository.save(balance);
    }
}
