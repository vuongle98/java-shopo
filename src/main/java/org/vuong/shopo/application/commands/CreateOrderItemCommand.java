package org.vuong.shopo.application.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderItemCommand implements Serializable {

    private Long productId;
    private int quantity;
}
