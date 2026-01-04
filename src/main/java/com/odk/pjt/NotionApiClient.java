package com.odk.pjt;

import com.odk.pjt.dto.NotionResponse;
import com.odk.pjt.dto.NotionResult;
import java.io.IOException;

public interface NotionApiClient {
    /**
     * Search all accessible pages and databases.
     * Used for creating an index of all resources the chatbot can access.
     */
    NotionResponse discover_resources() throws IOException, InterruptedException;

    /**
     * Query a specific database to get all page IDs within it.
     * 
     * @param databaseId The ID of the database to query.
     */
    NotionResponse get_pages_from_db(String databaseId) throws IOException, InterruptedException;

    /**
     * Retrieve metadata for a specific page.
     * Used to check last_edited_time for embedding updates.
     * 
     * @param pageId The ID of the page.
     */
    NotionResult get_page_metadata(String pageId) throws IOException, InterruptedException;

    /**
     * Retrieve all content blocks of a page, including nested blocks.
     * 
     * @param pageId The ID of the page.
     */
    String get_page_content(String pageId) throws IOException, InterruptedException;

    /**
     * Retrieve the schema of a database.
     * Used to save database properties as metadata in vector DB.
     * 
     * @param databaseId The ID of the database.
     */
    NotionResult get_db_schema(String databaseId) throws IOException, InterruptedException;
}
