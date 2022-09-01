package com.maveric.balanceservice.converter;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import org.springframework.stereotype.Component;

@Component
public class ModelDtoConverter {
    public BalanceDto entityToDto(Balance balance){
        BalanceDto balanceDto= new BalanceDto();

        balanceDto.setAccountId(balance.getAccountId());
        balanceDto.setCurrency(balance.getCurrency());
        balanceDto.setAmount(balance.getAmount());
        balanceDto.setId(balance.getId());
        balanceDto.setUpdatedAt(balance.getUpdatedAt());
        balanceDto.setCreatedAt(balance.getCreatedAt());

        return balanceDto;

    }

    public Balance dtoToEntity(BalanceDto balanceDto){
        Balance balance = new Balance();

        balance.setAccountId(balanceDto.getAccountId());
        balance.setAmount(balanceDto.getAmount());
        balance.setCurrency(balanceDto.getCurrency());

        return balance;
    }
}
