package com.odk.pjt;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class SimpleHttpClientTest {

    @Test
    public void test() throws IOException, InterruptedException {
        SimpleHttpClient client = new SimpleHttpClient("https://www.google.com");
        String response = client.get("/");

        System.out.println(response);
    }

}