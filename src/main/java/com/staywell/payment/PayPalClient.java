package com.staywell.payment;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
public class PayPalClient {
    /**
     * Set up PayPal SDK environment with PayPal access credentials. This sample uses SandboxEnvironment. In production, use LiveEnvironment.
     */
    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            System.getProperty("PAYPAL-CLIENT-ID") != null
                    ? System.getProperty("PAYPAL-CLIENT-ID")
                    : "PAYPAL-CLIENT-ID",
            System.getProperty("PAYPAL-CLIENT-SECRET") != null
                    ? System.getProperty("PAYPAL_CLIENT_SECRET")
                    : "PAYPAL-CLIENT-SECRET");
    /**
     * Returns PayPal HTTP client instance in an environment with access
     * credentials. Use this instance to invoke PayPal APIs, provided the
     * credentials have access.
     */
    PayPalHttpClient client = new PayPalHttpClient(environment);
    /**
     * Method to get client object
     *
     * @return PayPalHttpClient client
     */
    public PayPalHttpClient client() {
        return this.client;
    }
}

