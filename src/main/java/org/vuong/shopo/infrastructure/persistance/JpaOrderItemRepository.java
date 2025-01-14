package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.OrderItem;
import org.vuong.shopo.domain.repositories.OrderItemRepository;
import org.vuong.shopo.infrastructure.projection.OrderItemProjection;

@RepositoryRestResource(excerptProjection = OrderItemProjection.class, exported = false)
public interface JpaOrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemRepository {
}
