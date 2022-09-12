package com.maveric.balanceservice.exception;

public class AccountIdMismatchException extends RuntimeException {
    public AccountIdMismatchException(String account_id){
        super("User not belongs to account id "+ account_id);
    }

    public AccountIdMismatchException(String account_id,String message){
        super(account_id+" message");
    }
}
