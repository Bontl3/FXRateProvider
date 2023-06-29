package org.example;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FXRateProvider {
    // Variable to hold the single instance of the FXRateProvider
    private static FXRateProvider instance = null;

    // HttpClint Instance to be reused for all requests
    private HttpClient httpClient;

    // API base URL
    private static final String API_URL = "http://api.exchangerate-api.com/v4/latest/";

    // Private constructor to prevent direct instantiation.
    private FXRateProvider(){
        this.httpClient = HttpClient.newHttpClient();
    }

    // Get the singleton instance of the FXRateProvider.
    // We use "synchronised" to ensure that in a multi-threaded enviroment,
    // only one thread can execute this method at a time, preventing creation of multiple instances
    public static synchronized FXRateProvider getInstance(){
        if (instance == null){
            instance = new FXRateProvider();
        }
        return instance;
    }

    // Method to fetch exchange rate for of currencies.
    public String getRate(String baseCurrency, String targetCurrency){
        try{
            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL + baseCurrency)).build();

            // Send the request and get the response
            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response body as JSON
            JsonReader jsonReader = Json.createReader( new StringReader(response.body()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            // Extract and return the exchange rate
            return jsonObject.getJsonObject("rates").get(targetCurrency).toString();
        }catch(Exception e){
            // Print the stack trace for debugging purposes and return an empty string
            // **********I should come bag to log this exeption********
            e.printStackTrace();
        }
        return "";
    }
}
