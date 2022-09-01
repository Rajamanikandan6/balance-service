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
    void shouldReturnUserWhenUpdateBalanceInvoked() throws Exception {
        when(mockedBalanceRepository.findById("631061c4c45f78545a1ed042")).thenReturn(Optional.ofNullable(getSampleBalance()));
        when(modelDtoConverter.entityToDto(mockedBalanceRepository.save(getSampleBalance()))).thenReturn(getSampleDtoBalance());

        BalanceDto balance = balanceService.updateBalance(getSampleBalance(),"631061c4c45f78545a1ed042");
        assertNotNull(balance);
        assertSame(balance.getAccountId(),getSampleBalance().getAccountId());

    }

    @Test
    void shouldReturnUserWhenCreateBalanceInvoked() throws Exception {
        when(mockedBalanceRepository.save(any(Balance.class))).thenReturn(getSampleBalance());
        when(modelDtoConverter.entityToDto(any(Balance.class))).thenReturn(getSampleDtoBalance());

        BalanceDto balance = balanceService.createBalance(getSampleBalance());

        assertNotNull(balance);
        assertSame(balance.getAccountId(),getSampleBalance().getAccountId());

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
