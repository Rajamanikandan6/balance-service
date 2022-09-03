package com.maveric.balanceservice.validation;

import com.maveric.balanceservice.constant.Currency;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { CurrencyValidatorImplementation.class })
public @interface CurrencyValidation {
    Currency[] anyOfTheseCurrency();
    String message() default "Currency should be any of {anyOfTheseCurrency}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
