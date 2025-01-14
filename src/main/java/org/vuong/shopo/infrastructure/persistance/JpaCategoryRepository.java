package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.Category;
import org.vuong.shopo.domain.repositories.CategoryRepository;
import org.vuong.shopo.infrastructure.projection.CategoryProjection;

@RepositoryRestResource(excerptProjection = CategoryProjection.class)
public interface JpaCategoryRepository extends
        JpaRepository<Category, Long>,
        CategoryRepository {
}
