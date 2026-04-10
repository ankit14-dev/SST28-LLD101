package com.example.payments;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        Map<String, PaymentGateway> gateways = new HashMap<>();
        gateways.put("fastpay", new FastPayAdapter(new FastPayClient()));
        gateways.put("safecash", new SafeCashAdapter(new SafeCashClient()));

        OrderService fastPayService = new OrderService(selectGateway(gateways, "fastpay"));
        OrderService safeCashService = new OrderService(selectGateway(gateways, "safecash"));

        String id1 = fastPayService.charge("cust-1", 1299);
        String id2 = safeCashService.charge("cust-2", 1299);
        System.out.println(id1);
        System.out.println(id2);
    }

    private static PaymentGateway selectGateway(Map<String, PaymentGateway> gateways, String provider) {
        Objects.requireNonNull(gateways, "gateways");
        Objects.requireNonNull(provider, "provider");
        PaymentGateway gateway = gateways.get(provider);
        if (gateway == null) {
            throw new IllegalArgumentException("unknown provider: " + provider);
        }
        return gateway;
    }
}
