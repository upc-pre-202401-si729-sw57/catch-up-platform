package com.acme.catchup.platform.news.domain.model.queries;

public record GetFavoriteSourceByIdQuery(Long id) {
    public GetFavoriteSourceByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id cannot be null and must be greater than 0");
        }
    }
}
