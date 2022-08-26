package com.maveric.balanceservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "balance")
public class Balance {
    @Id
    private String id;

    private String accountId;

    private int amount;

    private String currency;
}
