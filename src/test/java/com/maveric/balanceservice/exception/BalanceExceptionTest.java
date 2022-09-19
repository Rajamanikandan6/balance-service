package com.maveric.balanceservice.exception;

import com.maveric.balanceservice.advice.GlobalControllerAdvice;
import com.maveric.balanceservice.dto.Error;
import com.maveric.balanceservice.model.Balance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceExceptionTest {

    @InjectMocks
    private GlobalControllerAdvice globalControllerAdvice;

    @Mock
    private Balance balance;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Test
    void shouldReturnErrorWhenBalanceNotFound() {
        BalanceNotFoundException exception = new BalanceNotFoundException("Balance not found");
        ResponseEntity<Error> error = globalControllerAdvice.balanceNotFound(exception);
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()),error.getBody().getCode());
    }

    @Test
    void shouldReturnErrorWhenAccountMismatch() {
        AccountIdMismatchException exception = new AccountIdMismatchException("Account id is not belongs to authenticate user");
        ResponseEntity<Error> error = globalControllerAdvice.accountUserMismatch(exception);
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()),error.getBody().getCode());
    }

    @Test
    void shouldReturnErrorWhenAccountIdAlreadyExist() {
        BalanceAlreadyExistException exception = new BalanceAlreadyExistException("Account Id already present");
        ResponseEntity<Error> error = globalControllerAdvice.handleDuplicateInput(exception);
        assertEquals(String.valueOf(HttpStatus.BAD_REQUEST.value()),error.getBody().getCode());
    }

}
