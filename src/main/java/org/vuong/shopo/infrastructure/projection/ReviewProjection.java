package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Review;

@Projection(name = "review", types = {Review.class})
public interface ReviewProjection {

    Long getId();

    Long getUserId();

    ProductProjection getProduct();

    String getComment();

    Integer getRating();
}
