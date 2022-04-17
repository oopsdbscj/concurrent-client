package com.cj;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConClient {
    private static String url = "http://localhost:8762/order/create/aabb";

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(8);

        while (true) {
            Thread.sleep(5);
            pool.submit(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(url))
                            .GET()
                            .build();
                    HttpResponse<String> response = HttpClient.newHttpClient()
                            .send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(Thread.currentThread().getName()
                            + "   " + response.statusCode()
                            + "  [" + response.body() + "] "
                            + LocalTime.now().toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }
    }
}
