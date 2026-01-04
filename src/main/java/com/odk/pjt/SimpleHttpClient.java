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

    public SimpleHttpClient(String host) {
        this();
        this.host = host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String get(String endpoint) throws IOException, InterruptedException {
        return get(endpoint, null);
    }

    public String get(String endpoint, Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(this.host + endpoint))
                .GET();

        applyHeaders(builder, headers);

        HttpRequest request = builder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String post(String endpoint) throws IOException, InterruptedException {
        return post(endpoint, null, null);
    }

    public String post(String endpoint, Map<String, String> body) throws IOException, InterruptedException {
        return post(endpoint, null, body);
    }

    public String post(String endpoint, Map<String, String> headers, Map<String, String> body)
            throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(this.host + endpoint));

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

    public String put(String endpoint) throws IOException, InterruptedException {
        return put(endpoint, null, null);
    }

    public String put(String endpoint, Map<String, String> body) throws IOException, InterruptedException {
        return put(endpoint, null, body);
    }

    public String put(String endpoint, Map<String, String> headers, Map<String, String> body)
            throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(this.host + endpoint));

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

    public String delete(String endpoint) throws IOException, InterruptedException {
        return delete(endpoint, null);
    }

    public String delete(String endpoint, Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(this.host + endpoint))
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
