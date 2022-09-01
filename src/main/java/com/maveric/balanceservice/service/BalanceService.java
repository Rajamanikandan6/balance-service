package com.maveric.balanceservice.service;

import com.maveric.balanceservice.constant.SuccessMessageConstant;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    public String deleteBalance(String balanceId){
        balanceRepository.findById(balanceId).orElseThrow(() -> new BalanceNotFoundException(balanceId));
        balanceRepository.deleteById(balanceId);
        return SuccessMessageConstant.DELETE_SUCCESS_MESSAGE;
    }

}
