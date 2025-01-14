package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.Review;
import org.vuong.shopo.domain.repositories.ReviewRepository;
import org.vuong.shopo.infrastructure.projection.ReviewProjection;

@RepositoryRestResource(excerptProjection = ReviewProjection.class)
public interface JpaReviewRepository extends JpaRepository<Review, Long>, ReviewRepository {
}
