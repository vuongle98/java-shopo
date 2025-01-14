package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Order;

@Projection(name = "order", types = {Order.class})
public interface OrderProjection {

    Long getId();

    Double getTotalPrice();

    Long getTotalAmount();

    String getCurrency();

    Order.OrderStatus getStatus();

//    List<OrderItemProjection> getOrderItems();
}
