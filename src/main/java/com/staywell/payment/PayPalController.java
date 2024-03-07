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

    /**
     * request structure postman
     *
     * POST /api/paypal/create-order HTTP/1.1
     * Host: localhost:8888
     * Content-Type: application/json
     * Content-Length: 55
     *
     * {
     *     "totalAmount": 100.00,
     *     "currency": "USD"
     * }
     *
     * @param orderRequest
     * @return
     */
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

    /**
     * api request structure postman
     *
     * POST /api/paypal/capture-order HTTP/1.1
     * Host: localhost:8888
     * Content-Type: application/json
     * Content-Length: 40
     *
     * {
     *     "orderId": "30090703DB043632U"
     * }
     *
     * @param orderRequest
     * @return Responses can be seen in
     * paypal account : https://developer.paypal.com/dashboard/dashboard/sandbox
     * I've checked, the request is logged in event logs of paypal sandbox;
     */
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
