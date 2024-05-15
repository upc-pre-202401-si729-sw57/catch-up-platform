package com.acme.catchup.platform.news.infrastructure.persistence.jpa;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteSourceRepository extends JpaRepository<FavoriteSource, Long> {
    boolean existsByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);
}
