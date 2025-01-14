package org.vuong.shopo.application.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vuong.shopo.domain.entities.Order;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFilter implements Serializable {

    private Long orderId;
    private Order.OrderStatus status;
}
