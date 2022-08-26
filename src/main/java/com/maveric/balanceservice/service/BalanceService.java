package com.maveric.balanceservice.service;


import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    public List<Balance> getAllBalance(String accountId){
        return balanceRepository.findAllByAccountId(accountId);
    }

}
