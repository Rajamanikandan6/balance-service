package com.maveric.balanceservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
