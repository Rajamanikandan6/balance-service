package com.maveric.balanceservice.service;

import com.maveric.balanceservice.constant.Currency;
import com.maveric.balanceservice.converter.ModelDtoConverter;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
 class BalanceServiceTest {

    @Mock
    private BalanceRepository mockedBalanceRepository;

    @Mock
    private ModelDtoConverter modelDtoConverter;

    @InjectMocks
    private BalanceService balanceService;


    @Test
    void shouldReturnBalanceWhenGetUserInvoked() throws Exception {
        when(mockedBalanceRepository.findByAccountIdAndBalanceId("631061c4c45f78545a1ed04","1")).thenReturn(Optional.of(getSampleBalance()));

        String balance = balanceService.getBalance("631061c4c45f78545a1ed04","1");

        assertNotNull(balance);
        assertSame(balance,getSampleBalance().getAmount());

    }

    public Balance getSampleBalance(){
        Balance balance = new Balance();
        balance.setCurrency(Currency.INR);
        balance.setAccountId("1");
        balance.setAmount("200");
        return balance;
    }

    public BalanceDto getSampleDtoBalance(){
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setCurrency(Currency.INR);
        balanceDto.setAccountId("1");
        balanceDto.setAmount("200");
        return balanceDto;
    }


}
