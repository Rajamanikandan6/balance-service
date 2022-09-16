package com.maveric.balanceservice.exception;

import com.maveric.balanceservice.advice.GlobalControllerAdvice;
import com.maveric.balanceservice.dto.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BalanceExceptionTest {

    @InjectMocks
    private GlobalControllerAdvice globalControllerAdvice;

    @Test
    void shouldReturnErrorWhenBalanceNotFound() {
        BalanceNotFoundException exception = new BalanceNotFoundException("Balance not found");
        ResponseEntity<Error> error = globalControllerAdvice.balanceNotFound(exception);
        assertEquals("404",error.getBody().getCode());
    }

    @Test
    void shouldReturnErrorWhenAccountMismatch() {
        AccountIdMismatchException exception = new AccountIdMismatchException("Account id is not belongs to authenticate user");
        ResponseEntity<Error> error = globalControllerAdvice.accountUserMismatch(exception);
        assertEquals("404",error.getBody().getCode());
    }

    @Test
    void shouldReturnErrorWhenAccountIdAlreadyExist() {
        BalanceAlreadyExistException exception = new BalanceAlreadyExistException("Account Id already present");
        ResponseEntity<Error> error = globalControllerAdvice.handleDuplicateInput(exception);
        assertEquals("400",error.getBody().getCode());
    }
}
