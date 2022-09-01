package com.maveric.balanceservice.service;

import com.maveric.balanceservice.constant.SuccessMessageConstant;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.converter.ModelDtoConverter;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;

import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    ModelDtoConverter modelDtoConverter;


    public String deleteBalance(String balanceId){
        balanceRepository.findById(balanceId).orElseThrow(() -> new BalanceNotFoundException(balanceId));
        balanceRepository.deleteById(balanceId);
        return SuccessMessageConstant.DELETE_SUCCESS_MESSAGE;
    }

    public BalanceDto updateBalance(Balance balance, String balanceId){
        Optional<Balance> balanceFromDb = balanceRepository.findById(balanceId);
            if(balanceFromDb.isPresent()) {
            Balance newBal = balanceFromDb.get();
            newBal.setAccountId(balance.getAccountId());
            newBal.setCurrency(balance.getCurrency());
            newBal.setAmount(balance.getAmount());


            return modelDtoConverter.entityToDto(balanceRepository.save(newBal));
        }else{
                throw  new BalanceNotFoundException(balanceId);
            }
    }
    public BalanceDto createBalance(Balance balance){

       return modelDtoConverter.entityToDto(balanceRepository.save(balance));
    }
}
