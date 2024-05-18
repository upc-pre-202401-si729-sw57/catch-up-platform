/**
 * FavoriteSourceCommandService.java
 * @summary
 * This file contains the FavoriteSourceCommandService interface.
 * This interface provides methods to handle commands related to FavoriteSource.
 */
package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;

import java.util.Optional;

/**
 * FavoriteSourceCommandService
 * @summary
 * Provides methods to handle commands related to FavoriteSource.
 */
public interface FavoriteSourceCommandService {
    /**
     *
     * @param command CreateFavoriteSourceCommand the command to handle including the newsApiKey and sourceId of the favorite source
     * @return Optional<FavoriteSource> the created favorite source
     */
    Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command);
}
