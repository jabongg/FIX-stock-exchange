package com.staywell.payment;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpRequest;
import com.paypal.orders.*;

import com.paypal.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class PayPalService {

    @Autowired
    private PayPalHttpClient payPalHttpClient;

    @Autowired
    PayPalConfig payPalConfig;

    @Value("${paypal.return.url}")
    private String returnUrl;

    @Value("${paypal.cancel.url}")
    private String cancelUrl;

    public String createOrder(BigDecimal totalAmount, String currency) throws IOException {
        HttpRequest<Order> request = new OrdersCreateRequest()
                .requestBody(buildOrderRequestBody(totalAmount, currency));
        //payPalHttpClient = payPalConfig.payPalHttpClient();
        HttpResponse<Order> response = payPalHttpClient.execute(request);
        return response.result().links().stream()
                .filter(link -> link.rel().equals("approve"))
                .findFirst()
                .map(LinkDescription::href)
                .orElseThrow(() -> new RuntimeException("No approve link found"));
    }

    public String captureOrder(String orderId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        HttpResponse<Order> response = payPalHttpClient.execute(request);
        return response.result().status();
    }

    private Order buildOrderRequestBody(BigDecimal totalAmount, String currency) {
        Order order = new Order();
        order.checkoutPaymentIntent("CAPTURE");
//        order.applicationContext(new ApplicationContext()
//                .returnUrl(returnUrl)
//                .cancelUrl(cancelUrl));
        List<PurchaseUnit> purchaseUnits = Collections.singletonList(new PurchaseUnit()
                .amountWithBreakdown(new AmountWithBreakdown()
                        .currencyCode(currency)
                        .value(totalAmount.toString())));
        order.purchaseUnits(purchaseUnits);
        return order;
    }
}
