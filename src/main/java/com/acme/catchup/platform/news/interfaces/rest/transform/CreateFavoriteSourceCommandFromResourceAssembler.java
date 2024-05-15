package com.acme.catchup.platform.news.interfaces.rest.transform;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.interfaces.rest.resources.CreateFavoriteSourceResource;

public class CreateFavoriteSourceCommandFromResourceAssembler {
    public static CreateFavoriteSourceCommand toCommandFromResource(CreateFavoriteSourceResource resource) {
        return new CreateFavoriteSourceCommand(resource.newsApiKey(), resource.sourceId());
    }
}
