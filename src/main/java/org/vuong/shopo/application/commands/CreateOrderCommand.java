package org.vuong.shopo.application.commands;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vuong.shopo.domain.entities.Order;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderCommand implements Serializable {

    @NotNull
    private List<CreateOrderItemCommand> orderItems;

    @NotNull
    private String currency;

    @NotNull
    private Long userId;

    @NotNull
    private Order.OrderStatus status;
}
