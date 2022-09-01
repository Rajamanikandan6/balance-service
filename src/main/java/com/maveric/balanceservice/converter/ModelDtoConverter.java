package com.maveric.balanceservice.converter;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelDtoConverter {
    public List<BalanceDto> entityToDto(List<Balance> user) {
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
}
