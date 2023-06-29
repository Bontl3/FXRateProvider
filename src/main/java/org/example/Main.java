package org.example;

public class Main {
    public static void main(String[] args) {
        FXRateProvider fxRateProvider = FXRateProvider.getInstance();
        String rate = fxRateProvider.getRate("USD", "EUR");
        System.out.println("Exchange rate from USD to EUR: " + rate);
    }
}
