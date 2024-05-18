package com.acme.catchup.platform.news.infrastructure.persistence.jpa.repositories;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteSourceRepository extends JpaRepository<FavoriteSource, Long> {
    boolean existsByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);
    Optional<FavoriteSource> findByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);
    List<FavoriteSource> findAllByNewsApiKey(String newsApiKey);
}
