package com.maveric.balanceservice.model;

import com.maveric.balanceservice.constant.Currency;
import com.maveric.balanceservice.validation.CurrencyValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@Document(collection = "balance")
public class Balance {

    @Id
    private String id;


    @NotBlank(message = "accountId shouldn't be empty")
    private String accountId;


    @NotBlank(message = "amount shouldn't be empty")
    @Pattern(regexp = "^[+]?(\\d+\\.?\\d*|\\.\\d+)$",message = "Invalid amount given")
    private String amount;

    @Enumerated(EnumType.STRING)
    @CurrencyValidation(anyOfTheseCurrency = {Currency.INR, Currency.EURO,Currency.DOLLAR})
    private Currency currency;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column(updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

}
