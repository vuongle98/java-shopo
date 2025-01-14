package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.Order;
import org.vuong.shopo.domain.repositories.BaseRepository;
import org.vuong.shopo.domain.repositories.OrderRepository;
import org.vuong.shopo.infrastructure.projection.OrderProjection;

@RepositoryRestResource(excerptProjection = OrderProjection.class, exported = false)
public interface JpaOrderRepository extends
        JpaRepository<Order, Long>,
        OrderRepository,
        BaseRepository<Order> {
}
