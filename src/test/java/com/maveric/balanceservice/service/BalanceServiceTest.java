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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
 class BalanceServiceTest {

    @Mock
    private BalanceRepository mockedBalanceRepository;

    @InjectMocks
    private BalanceService balanceService;


    @Test
    void shouldReturnUserWhenGetUserInvoked() throws Exception {
        when(mockedBalanceRepository.findById("631061c4c45f78545a1ed042")).thenReturn(Optional.of(getSampleBalance()));

        Optional<Balance> balance = mockedBalanceRepository.findById("631061c4c45f78545a1ed042");
        assertNotNull(balance);
        assertSame(balance.get().getAccountId(),getSampleBalance().getAccountId());
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
