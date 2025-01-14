package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.Shop;
import org.vuong.shopo.domain.repositories.ShopRepository;
import org.vuong.shopo.infrastructure.projection.ShopProjection;

@RepositoryRestResource(excerptProjection = ShopProjection.class)
public interface JpaShopRepository extends JpaRepository<Shop, Long>, ShopRepository {
}
