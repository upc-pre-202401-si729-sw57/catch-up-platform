package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;

import java.util.List;
import java.util.Optional;

public interface FavoriteSourceQueryService {
    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);
    Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query);
    List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query);
}
