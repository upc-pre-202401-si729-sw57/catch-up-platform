package com.acme.catchup.platform.news.interfaces.rest.resources;

public record CreateFavoriteSourceResource(String newsApiKey, String sourceId) {
    public CreateFavoriteSourceResource {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        if (sourceId == null || sourceId.isBlank())
            throw new IllegalArgumentException("sourceId cannot be null or empty");
    }
}
