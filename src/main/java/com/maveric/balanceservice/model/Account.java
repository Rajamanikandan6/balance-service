package com.maveric.balanceservice.model;

import com.maveric.balanceservice.constant.Type;
import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private String _id;

    private Type type;

    private String customerId;

    private Date createdAt;

    private Date updatedAt;
}
