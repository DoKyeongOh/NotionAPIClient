package com.odk.pjt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.odk.pjt.dto.NotionResponse;
import com.odk.pjt.dto.NotionResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NotionApiClientImpl implements NotionApiClient {

    private final SimpleHttpClient httpClient;
    private final String apiKey;
    private final String notionVersion = "2025-09-03";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotionApiClientImpl(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = new SimpleHttpClient("https://api.notion.com");
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        headers.put("Notion-Version", notionVersion);
        return headers;
    }

    @Override
    public NotionResponse discover_resources() throws IOException, InterruptedException {
        Map<String, String> body = new HashMap<>();
        // Search all by default
        String response = httpClient.post("/v1/search", getHeaders(), body);
        return objectMapper.readValue(response, NotionResponse.class);
    }

    @Override
    public NotionResponse get_pages_from_db(String databaseId) throws IOException, InterruptedException {
        Map<String, String> body = new HashMap<>();
        String response = httpClient.post("/v1/databases/" + databaseId + "/query", getHeaders(), body);
        return objectMapper.readValue(response, NotionResponse.class);
    }

    @Override
    public NotionResult get_page_metadata(String pageId) throws IOException, InterruptedException {
        String response = httpClient.get("/v1/pages/" + pageId, getHeaders());
        return objectMapper.readValue(response, NotionResult.class);
    }

    @Override
    public String get_page_content(String pageId) throws IOException, InterruptedException {
        return get_block_children_recursive(pageId);
    }

    private String get_block_children_recursive(String blockId) throws IOException, InterruptedException {
        String response = httpClient.get("/v1/blocks/" + blockId + "/children", getHeaders());

        JsonNode root = objectMapper.readTree(response);
        ArrayNode results = (ArrayNode) root.get("results");

        if (results != null) {
            for (JsonNode block : results) {
                if (block.get("has_children").asBoolean()) {
                    String childrenResponse = get_block_children_recursive(block.get("id").asText());
                    JsonNode childrenRoot = objectMapper.readTree(childrenResponse);
                    ((ObjectNode) block).set("children", childrenRoot.get("results"));
                }
            }
        }

        return objectMapper.writeValueAsString(root);
    }

    @Override
    public NotionResult get_db_schema(String databaseId) throws IOException, InterruptedException {
        String response = httpClient.get("/v1/databases/" + databaseId, getHeaders());
        return objectMapper.readValue(response, NotionResult.class);
    }
}
