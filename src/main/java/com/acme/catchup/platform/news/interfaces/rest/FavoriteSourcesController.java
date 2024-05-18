/**
 * FavoriteSourcesController.java
 * @summary
 * This class is a REST controller that handles HTTP requests related to favorite sources.
 * It provides endpoints to create a favorite source, get a favorite source by id, get all favorite sources by news API key,
 * @author
 * { author.name } { author.email
 */
package com.acme.catchup.platform.news.interfaces.rest;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.interfaces.rest.resources.CreateFavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.rest.resources.FavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.rest.transform.CreateFavoriteSourceCommandFromResourceAssembler;
import com.acme.catchup.platform.news.interfaces.rest.transform.FavoriteSourceResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * FavoriteSourcesController
 * @summary
 * This class is a REST controller that handles HTTP requests related to favorite sources.
 * It provides endpoints to create a favorite source, get a favorite source by id, get all favorite sources by news API key,
 */
@RestController
@RequestMapping("/api/v1/favorite-sources")
public class FavoriteSourcesController {
    private final FavoriteSourceCommandService favoriteSourceCommandService;
    private final FavoriteSourceQueryService favoriteSourceQueryService;

    public FavoriteSourcesController(FavoriteSourceCommandService favoriteSourceCommandService, FavoriteSourceQueryService favoriteSourceQueryService) {
        this.favoriteSourceCommandService = favoriteSourceCommandService;
        this.favoriteSourceQueryService = favoriteSourceQueryService;
    }

    /**
     * Create a favorite source
     * @param resource The resource containing the favorite source data including the news API key and the source ID
     * @return The created favorite source resource, or a bad request response if the favorite source could not be created
     */
    @PostMapping
    public ResponseEntity<FavoriteSourceResource> createFavoriteSource(@RequestBody CreateFavoriteSourceResource resource) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceCommandService.handle(
                CreateFavoriteSourceCommandFromResourceAssembler.toCommandFromResource(resource));
        return favoriteSource.map(source -> new ResponseEntity<>(
                FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Get a favorite source by ID
     * @param id The ID of the favorite source
     * @return The favorite source resource, or a not found response if the favorite source does not exist
     */
    @GetMapping("{id}")
    public ResponseEntity<FavoriteSourceResource> getFavoriteSourceById(@PathVariable Long id) {
        var getFavoriteSourceByIdQuery = new GetFavoriteSourceByIdQuery(id);
        Optional<FavoriteSource> favoriteSource = favoriteSourceQueryService.handle(getFavoriteSourceByIdQuery);
        if (favoriteSource.isEmpty()) return ResponseEntity.notFound().build();
        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Get all favorite sources by news API key
     * @param newsApiKey The news API key
     * @return The list of favorite source resources, or a not found response if no favorite sources exist for the news API key
     */
    private ResponseEntity<List<FavoriteSourceResource>> getAllFavoriteSourcesByNewsApiKey(String newsApiKey) {
        var getAllFavoriteSourcesByNewsApiKeyQuery = new GetAllFavoriteSourcesByNewsApiKeyQuery(newsApiKey);
        List<FavoriteSource> favoriteSources = favoriteSourceQueryService.handle(getAllFavoriteSourcesByNewsApiKeyQuery);
        if (favoriteSources.isEmpty()) return ResponseEntity.notFound().build();
        var favoriteSourceResources = favoriteSources.stream().map(FavoriteSourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(favoriteSourceResources);
    }

    /**
     * Get a favorite source by news API key and source ID
     * @param newsApiKey The news API key
     * @param sourceId The source ID
     * @return The favorite source resource, or a not found response if the favorite source does not exist
     */
    private ResponseEntity<FavoriteSourceResource> getFavoriteSourceByNewsApiKeyAndSourceId(String newsApiKey, String sourceId) {
        var getFavoriteSourceByNewsApiKeyAndSourceIdQuery = new GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(newsApiKey, sourceId);
        Optional<FavoriteSource> favoriteSource = favoriteSourceQueryService.handle(getFavoriteSourceByNewsApiKeyAndSourceIdQuery);
        if (favoriteSource.isEmpty()) return ResponseEntity.notFound().build();
        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Get favorite sources with parameters
     * @param params The parameters to filter the favorite sources by
     * @return The favorite source resource, or a bad request response if the parameters are invalid
     */
    @GetMapping
    public ResponseEntity<?> getFavoriteSourcesWithParameters(@RequestParam Map<String, String> params) {
        if (params.containsKey("newsApiKey") && params.containsKey("sourceId")) {
            return getFavoriteSourceByNewsApiKeyAndSourceId(params.get("newsApiKey"), params.get("sourceId"));
        } else if (params.containsKey("newsApiKey")) {
            return getAllFavoriteSourcesByNewsApiKey(params.get("newsApiKey"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
