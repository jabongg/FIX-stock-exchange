package com.staywell.payment;

import lombok.Data;

@Data
public class CaptureOrderRequest {
    private String orderId;
}
