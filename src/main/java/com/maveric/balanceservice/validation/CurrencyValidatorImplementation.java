package com.maveric.balanceservice.validation;

import com.maveric.balanceservice.constant.Currency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CurrencyValidatorImplementation implements ConstraintValidator<CurrencyValidation,Currency>{

    private Currency[] allCurrencyValue;

    public CurrencyValidatorImplementation(CurrencyValidation currencyValidation) {
        this.allCurrencyValue = currencyValidation.anyOfTheseCurrency();
    }

    @Override
    public boolean isValid(Currency currency, ConstraintValidatorContext constraintValidatorContext) {
        return currency == null || Arrays.asList(allCurrencyValue).contains(allCurrencyValue);
    }
}
