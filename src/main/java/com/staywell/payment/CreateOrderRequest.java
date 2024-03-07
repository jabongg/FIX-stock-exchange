package com.staywell.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {
    private BigDecimal totalAmount;
    private String currency;
}
