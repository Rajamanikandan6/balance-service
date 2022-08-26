package com.maveric.balanceservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "balance")
public class Balance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String accountId;

    private int amount;

    private String currency;
}
