package com.maveric.balanceservice.service;

import com.maveric.balanceservice.converter.ModelDtoConverter;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    ModelDtoConverter modelDtoConverter;

    public BalanceDto createBalance(Balance balance){

       return modelDtoConverter.entityToDto(balanceRepository.save(balance));
    }
}
