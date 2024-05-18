/**
 * FavoriteSourceCommandServiceImpl.java
 * @summary
 * The FavoriteSourceCommandServiceImpl class is an implementation of the FavoriteSourceCommandService interface.
 * It provides the implementation for the handle method, which creates a new FavoriteSource object and saves it to the database.
 * @author
 * { author.name } { author.email }
 */
package com.acme.catchup.platform.news.application.internal.commandservices;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.repositories.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * FavoriteSourceCommandServiceImpl
 * @summary
 * FavoriteSourceCommandServiceImpl is an implementation of the FavoriteSourceCommandService interface.
 * It provides the implementation for the handle method, which creates a new FavoriteSource object and saves it to the database.
 */
@Service
public class FavoriteSourceCommandServiceImpl implements FavoriteSourceCommandService {
    private final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceCommandServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {
        this.favoriteSourceRepository = favoriteSourceRepository;
    }

    /**
     * CreateFavoriteSourceCommand handler
     * @param command CreateFavoriteSourceCommand the command to handle including the newsApiKey and sourceId of the favorite source
     * @return Optional<FavoriteSource> the created favorite source
     */
    @Override
    public Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command) {
        if (favoriteSourceRepository.existsByNewsApiKeyAndSourceId(command.newsApiKey(), command.sourceId()))
            throw new IllegalArgumentException("Favorite source already exists with same sourceId and NewsApiKey");
        var favoriteSource = new FavoriteSource(command);
        var createdFavoriteSource = favoriteSourceRepository.save(favoriteSource);
        return Optional.of(createdFavoriteSource);
    }
}
