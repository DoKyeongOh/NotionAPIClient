package com.odk.pjt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class SimpleHttpClient {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    
    private String host;

    public SimpleHttpClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String get() throws IOException, InterruptedException {
        return get(null);
    }

    public String get(Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(this.host))
                .GET();

        applyHeaders(builder, headers);

        HttpRequest request = builder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String post() throws IOException, InterruptedException {
        return post(null, null);
    }

    public String post(Map<String, String> body) throws IOException, InterruptedException {
        return post(null, body);
    }

    public String post(Map<String, String> headers, Map<String, String> body) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(this.host));

        if (body != null) {
            String jsonBody = objectMapper.writeValueAsString(body);
            builder.header("Content-Type", "application/json")
                   .POST(HttpRequest.BodyPublishers.ofString(jsonBody));
        } else {
            builder.POST(HttpRequest.BodyPublishers.noBody());
        }

        applyHeaders(builder, headers);

        HttpRequest request = builder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String put() throws IOException, InterruptedException {
        return put(null, null);
    }

    public String put(Map<String, String> body) throws IOException, InterruptedException {
        return put(null, body);
    }

    public String put(Map<String, String> headers, Map<String, String> body) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(this.host));

        if (body != null) {
            String jsonBody = objectMapper.writeValueAsString(body);
            builder.header("Content-Type", "application/json")
                   .PUT(HttpRequest.BodyPublishers.ofString(jsonBody));
        } else {
            builder.PUT(HttpRequest.BodyPublishers.noBody());
        }

        applyHeaders(builder, headers);

        HttpRequest request = builder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String delete() throws IOException, InterruptedException {
        return delete(null);
    }

    public String delete(Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(this.host))
                .DELETE();

        applyHeaders(builder, headers);

        HttpRequest request = builder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private void applyHeaders(HttpRequest.Builder builder, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(builder::header);
        }
    }
}
