package org.vuong.shopo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vuong.shopo.domain.entities.Order;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    private Long id;
    private String currency;

    private List<OrderItemDto> orderItems;
    private Order.OrderStatus status;
    private Long userId;
}
