/**
 * FavoriteSourceQueryService.java
 * @summary
 * This file contains the FavoriteSourceQueryService interface.
 * This interface provides methods to handle queries related to FavoriteSource.
 * @author
 * { author.name } { author.email }
 */
package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * FavoriteSourceQueryService
 * @summary
 * Provides methods to handle queries related to FavoriteSource.
 */
public interface FavoriteSourceQueryService {
    /**
     * Query Handler for GetFavoriteSourceByIdQuery
     * @param query GetFavoriteSourceByIdQuery the query to handle including the id of the favorite source
     * @return Optional<FavoriteSource>
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);

    /**
     * Query Handler for GetFavoriteSourceByNewsApiKeyAndSourceIdQuery
     * @param query GetFavoriteSourceByNewsApiKeyAndSourceIdQuery the query to handle including the newsApiKey and sourceId of the favorite source
     * @return Optional<FavoriteSource>
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query);

    /**
     * Query Handler for GetAllFavoriteSourcesByNewsApiKeyQuery
     * @param query GetAllFavoriteSourcesByNewsApiKeyQuery the query to handle including the newsApiKey of the favorite sources
     * @return List<FavoriteSource>
     */
    List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query);
}
