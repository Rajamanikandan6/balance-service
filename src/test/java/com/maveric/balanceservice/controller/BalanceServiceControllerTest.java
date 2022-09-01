package com.maveric.balanceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.balanceservice.repository.BalanceRepository;
import com.maveric.balanceservice.service.BalanceService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
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
    void shouldGetUserWhenRequestMadeToGetUser() throws Exception {
        mvc.perform(get(API_V1_BALANCE + "?page=0&pageSize=10"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldReturnInternalServerWhenDbReturnsErrorForGetUsers() throws Exception{
        when(balanceService.getAllBalance("1",0,10)).thenThrow(new IllegalArgumentException());
        mvc.perform(get(API_V1_BALANCE+"?page=0&pageSize=10"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
