package com.maveric.balanceservice.feignclient;

import com.maveric.balanceservice.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "feignUser",url = "http://localhost:3010/api/v1/")
public interface AccountFeignService {
    @GetMapping("/customers/{customerId}/accounts")
    ResponseEntity<List<Account>> getAccounts(@PathVariable String customerId);
}
