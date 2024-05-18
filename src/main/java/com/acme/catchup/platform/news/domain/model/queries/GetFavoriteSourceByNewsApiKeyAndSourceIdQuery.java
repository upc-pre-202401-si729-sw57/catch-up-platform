package com.acme.catchup.platform.news.domain.model.queries;

public record GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(String newsApiKey, String sourceId) {
    public GetFavoriteSourceByNewsApiKeyAndSourceIdQuery {
        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        }
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId cannot be null or empty");
        }
    }
}
