package com.maveric.balanceservice.service;


import com.maveric.balanceservice.converter.ModelDtoConverter;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    ModelDtoConverter modelDtoConverter;

    public List<BalanceDto> getAllBalance(String accountId, int page, int pageSize){

        Page<Balance> balances = balanceRepository.findAllByAccountId(PageRequest.of(page, pageSize),accountId);
        List<Balance> listBalance = balances.getContent();
        return modelDtoConverter.entityToDto(listBalance);
    }

}
