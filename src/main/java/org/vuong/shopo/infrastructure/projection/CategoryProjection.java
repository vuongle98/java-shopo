package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Category;

@Projection(name = "category", types = {Category.class})
public interface CategoryProjection {

    Long getId();

    String getName();

    String getDescription();

    String getSlug();

}
