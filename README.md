# FX Rate Provider - Java Singleton Service

The FX Rate Provider is a lightweight Java application that provides access to real-time foreign exchange rates. The project demonstrates the use of the Singleton design pattern in a practical context.

## Overview

The application leverages a free, publicly accessible API to fetch the latest foreign exchange rates. The Singleton design pattern ensures that only one instance of the service is created and used throughout the application. This not only optimizes resource usage but also provides a consistent and centralized point of access to the service.

The `FXRateProvider` class encapsulates the logic for fetching the exchange rates. It uses the Java `HttpClient` to send requests to the API and the `javax.json` library for parsing the JSON responses.

## Features

- Singleton FX Rate Provider service.
- Uses Java's built-in HTTP Client.
- Parses JSON responses with `javax.json`.
- Synchronized method to ensure thread safety.

## Usage

Simply get the singleton instance of `FXRateProvider` and call the `getRate(String baseCurrency, String targetCurrency)` method, providing the base and target currencies as arguments.

```java
FXRateProvider fxRateProvider = FXRateProvider.getInstance();
String rate = fxRateProvider.getRate("USD", "EUR");
System.out.println("Exchange rate from USD to EUR: " + rate);
```
