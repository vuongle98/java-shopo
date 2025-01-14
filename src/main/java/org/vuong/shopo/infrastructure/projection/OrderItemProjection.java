package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.OrderItem;

@Projection(name = "order-item", types = {OrderItem.class})
public interface OrderItemProjection {

    Long getId();

    OrderProjection getOrder();

    ProductProjection getProduct();

    Double getPrice();

    Integer getQuantity();
}
