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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.willDoNothing;
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
    void shouldReturnBalanceWhenGetBalanceInvoked() throws Exception {
        when(mockedBalanceRepository.findByAccountIdAndBalanceId("631061c4c45f78545a1ed04","1")).thenReturn(Optional.of(getSampleBalance()));

        String balance = balanceService.getBalance("631061c4c45f78545a1ed04","1");

        assertNotNull(balance);
        assertSame(balance,getSampleBalance().getAmount());

    }

    @Test
    void shouldReturnBalancesWhenGetBalancesInvoked() throws Exception {
        Page<Balance> pageResponse = new PageImpl<>(Arrays.asList(getSampleBalance(),getSampleBalance()));
        when(mockedBalanceRepository.findAllByAccountId(any(Pageable.class),eq("1"))).thenReturn(pageResponse);
        when(pageResponse.hasContent()).thenReturn(true);
        when(pageResponse.getContent()).thenReturn(Arrays.asList(getSampleBalance(),getSampleBalance()));
        when(modelDtoConverter.entityToDto(any(Balance.class))).thenReturn((BalanceDto) Arrays.asList(getSampleDtoBalance(),getSampleDtoBalance()));

        List<BalanceDto> balance = balanceService.getAllBalance("1",10,1);
        assertNotNull(balance);
        assertSame(Currency.INR,balance.get(0).getCurrency());
    }
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

    @Test
    void shouldReturnMessageWhenDeleteBalanceInvoked() throws Exception {
        when(mockedBalanceRepository.findById("631061c4c45f78545a1ed042")).thenReturn(Optional.of(getSampleBalance()));
        willDoNothing().given(mockedBalanceRepository).deleteById("631061c4c45f78545a1ed042");

        String message = balanceService.deleteBalance("631061c4c45f78545a1ed042");

        assertNotNull(message);
        assertSame(message , "Balance Deleted Successfully");
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
