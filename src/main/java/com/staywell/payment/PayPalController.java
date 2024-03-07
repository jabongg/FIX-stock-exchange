package com.staywell.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
public class PayPalController {

    @Autowired
    private PayPalService payPalService;

    @PostMapping("/api/paypal/create-order")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest orderRequest) {
        try {
            BigDecimal totalAmount = orderRequest.getTotalAmount();
            String currency = orderRequest.getCurrency();
            String approvalLink = payPalService.createOrder(totalAmount, currency);
            return ResponseEntity.ok(approvalLink);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order");
        }
    }
    @PostMapping("/api/paypal/capture-order")
    public ResponseEntity<String> captureOrder(@RequestBody CaptureOrderRequest orderRequest) {
        try {
            String orderId = orderRequest.getOrderId();
            String captureStatus = payPalService.captureOrder(orderId);
            return ResponseEntity.ok("Order captured successfully. Status: " + captureStatus);
        } catch (IOException e) {
            // Check if the error is ORDER_NOT_APPROVED
            if (e.getMessage().contains("ORDER_NOT_APPROVED")) {
                // Handle ORDER_NOT_APPROVED error
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Payer has not approved the order for payment. Please redirect the payer to approve the order.");
            } else {
                // Handle other errors
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to capture order");
            }
        }
    }
}
