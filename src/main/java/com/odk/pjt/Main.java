package com.odk.pjt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.odk.pjt.dto.NotionResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello World!");

        Map<String, String> configMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try (InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("config.yml")) {
            if (resourceAsStream == null) {
                System.out.println("config.yml을 찾을 수 없습니다.");
                return;
            }
            configMap.putAll(mapper.readValue(resourceAsStream, new TypeReference<Map<String, String>>() {
            }));
            System.out.println("Config Loaded: " + configMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String apiKey = configMap.get("apiKey");

        NotionApiClientImpl client = new NotionApiClientImpl(apiKey);
        NotionResponse notionResponse = client.discover_resources();
    }
}
