package com.staywell.payment;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {

    //@Value("${paypal.client.id}")
    private String clientId = "AcbUSraRkNFpYvE57a4exCfXyYtydUO2mD6dp6r7YQN0mS1nm4Jy0OaIK7kOGL5igE-zg8Y72BBZjXxO";

    //@Value("${paypal.client.secret}")
    private String clientSecret="EMA53mPb1SsRk1hRx1ffQydolXRSAFOQaw2gy9CPOiuVN2F_q5km9NK2ws8YorYB6P7ASvS0tJdh3MC5";

    @Getter
    private PayPalEnvironment environment;

    public PayPalConfig() {
        this.environment = new PayPalEnvironment.Sandbox(clientId, clientSecret);
    }

    @Bean
    public PayPalHttpClient payPalHttpClient() {
        return new PayPalHttpClient(getEnvironment());
    }

}

