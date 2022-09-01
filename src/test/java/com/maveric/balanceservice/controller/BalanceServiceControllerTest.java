package com.maveric.balanceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.constant.Currency;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import com.maveric.balanceservice.service.BalanceService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BalanceServiceController.class)
@Tag("Integration tests")
 class BalanceServiceControllerTest {

    private static final String API_V1_BALANCE = "/api/v1/accounts/1/balances";

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private BalanceService balanceService;

    @MockBean
    private BalanceRepository balanceRepository;

    @Test
    void shouldDeleteBalanceWhenRequestMadeToDeleteBalace() throws Exception{
        mvc.perform(delete(API_V1_BALANCE+"/631061c4c45f78545a1ed042"))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void shouldReturnInternalServerWhenDbReturnsErrorForDelete() throws Exception{
        when(balanceService.deleteBalance("631061c4c45f78545a1ed04")).thenThrow(new BalanceNotFoundException("631061c4c45f78545a1ed04"));
        mvc.perform(delete(API_V1_BALANCE+"/631061c4c45f78545a1ed04"))
                .andExpect(status().isNotFound())
                .andDo(print());

    }
    @Test
    void shouldUpdateBalanceWhenRequestMadeToUpdateBalance() throws Exception{
        mvc.perform(put(API_V1_BALANCE+"/631061c4c45f78545a1ed042").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleBalance())))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void shouldCreateBalanceWhenRequestMadeToCreateBalance() throws Exception{
        mvc.perform(post(API_V1_BALANCE).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleBalance())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldThrowBadRequestWhenBalanceDetailsAreWrongForUpdate() throws Exception{
        Balance balance = new Balance();
        balance.setCurrency(Currency.INR);
        balance.setAccountId(null);
        balance.setAmount("200");
        mvc.perform(put(API_V1_BALANCE+"/631061c4c45f78545a1ed042").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(balance)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void shouldThrowBadRequestWhenBalanceDetailsAreWrongForCreate() throws Exception{
        Balance balance = new Balance();
        balance.setCurrency(Currency.INR);
        balance.setAccountId(null);
        balance.setAmount("200");
        mvc.perform(post(API_V1_BALANCE).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(balance)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void shouldReturnInternalServerWhenDbReturnsErrorForUpdate() throws Exception{
        when(balanceService.updateBalance(Mockito.any(Balance.class),eq("631061c4c45f78545a1ed042"))).thenThrow(new IllegalArgumentException());
        mvc.perform(post(API_V1_BALANCE+"/631061c4c45f78545a1ed042").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleBalance())))
                .andExpect(status().isInternalServerError())
                .andDo(print());

    }
    @Test
    void shouldReturnInternalServerWhenDbReturnsErrorForCreate() throws Exception{
        when(balanceService.createBalance(Mockito.any(Balance.class))).thenThrow(new IllegalArgumentException());
        mvc.perform(post(API_V1_BALANCE).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleBalance())))
                .andExpect(status().isInternalServerError())
                .andDo(print());

    }


    public Balance getSampleBalance(){

       Balance balance = new Balance();
       balance.setCurrency(Currency.INR);
       balance.setAccountId("1");
       balance.setAmount("200");
        return balance;
    }
}
