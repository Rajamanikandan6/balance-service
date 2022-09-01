package com.maveric.balanceservice.model;

import com.maveric.balanceservice.constant.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Document(collection = "balance")
public class Balance {

    @Id
    private String id;

    @NotNull
    @NotBlank
    private String accountId;

    @NotNull
    @NotBlank
    private String amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

}
