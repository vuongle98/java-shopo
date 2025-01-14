package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.vuong.shopo.domain.entities.Product;
import org.vuong.shopo.domain.repositories.BaseQueryRepository;
import org.vuong.shopo.domain.repositories.BaseRepository;
import org.vuong.shopo.domain.repositories.ProductRepository;
import org.vuong.shopo.infrastructure.projection.ProductProjection;

import java.util.List;

@RepositoryRestResource(excerptProjection = ProductProjection.class)
public interface JpaProductRepository extends
        JpaRepository<Product, Long>,
        ProductRepository,
        BaseRepository<Product>,
        BaseQueryRepository<Product> {

    @RestResource(path = "find-deleted-products", rel = "products")
    @Query("SELECT p FROM Product p WHERE p.deletedAt IS NOT NULL")
    List<Product> findDeletedProducts();
}
