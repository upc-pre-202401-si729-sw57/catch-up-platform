package com.acme.catchup.platform.news.domain.model.queries;

public record GetAllFavoriteSourcesByNewsApiKeyQuery(String newsApiKey) {
    public GetAllFavoriteSourcesByNewsApiKeyQuery {
        if (newsApiKey == null || newsApiKey.isEmpty())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
    }
}
