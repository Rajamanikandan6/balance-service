package com.maveric.balanceservice.converter;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelDtoConverter {
    public List<BalanceDto> entityToDtoList(List<Balance> user) {
        List<BalanceDto> balanceDto = new ArrayList<>();
        user.stream().forEach(b -> {
            BalanceDto singleBalace = new BalanceDto();
            singleBalace.setCreatedAt(b.getCreatedAt());
            singleBalace.setId(b.getId());
            singleBalace.setUpdatedAt(b.getUpdatedAt());
            singleBalace.setAmount(b.getAmount());
            singleBalace.setCurrency(b.getCurrency());
            singleBalace.setAccountId(b.getAccountId());

            balanceDto.add(singleBalace);
        });
        return balanceDto;
    }

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
