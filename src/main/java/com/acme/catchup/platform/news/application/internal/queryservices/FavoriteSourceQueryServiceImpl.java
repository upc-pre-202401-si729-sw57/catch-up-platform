/**
 * FavoriteSourceQueryServiceImpl
 * @summary
 * FavoriteSourceQueryServiceImpl is an implementation of the FavoriteSourceQueryService interface.
 * It provides the implementation for the handle method, which retrieves favorite sources from the database.
 * @author
 * { author.name } { author.email }
 */
package com.acme.catchup.platform.news.application.internal.queryservices;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.repositories.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * FavoriteSourceQueryServiceImpl
 * @summary
 * FavoriteSourceQueryServiceImpl is an implementation of the FavoriteSourceQueryService interface.
 * It provides the implementation for the handle method, which retrieves favorite sources from the database.
 *
 */
@Service
public class FavoriteSourceQueryServiceImpl implements FavoriteSourceQueryService {
    private final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceQueryServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {
        this.favoriteSourceRepository = favoriteSourceRepository;
    }

    /**
     * GetFavoriteSourceByIdQuery handler
     * @param query GetFavoriteSourceByIdQuery the query to handle including the id of the favorite source
     * @return Optional<FavoriteSource> the favorite source with the given id
     */
    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query) {
        return favoriteSourceRepository.findById(query.id());
    }

    /**
     * GetFavoriteSourceByNewsApiKeyAndSourceIdQuery handler
     * @param query GetFavoriteSourceByNewsApiKeyAndSourceIdQuery the query to handle including the newsApiKey and sourceId of the favorite source
     * @return Optional<FavoriteSource> the favorite source with the given newsApiKey and sourceId
     */
    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query) {
        return favoriteSourceRepository.findByNewsApiKeyAndSourceId(query.newsApiKey(), query.sourceId());
    }

    /**
     * GetAllFavoriteSourcesByNewsApiKeyQuery handler
     * @param query GetAllFavoriteSourcesByNewsApiKeyQuery the query to handle including the newsApiKey of the favorite sources
     * @return List<FavoriteSource> the list of favorite sources with the given newsApiKey
     */
    @Override
    public List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query) {
        return favoriteSourceRepository.findAllByNewsApiKey(query.newsApiKey());
    }
}
