package org.vuong.shopo.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vuong.shopo.application.commands.CreateOrderCommand;
import org.vuong.shopo.application.dto.OrderDto;
import org.vuong.shopo.application.queries.OrderFilter;


public interface OrderService {

    OrderDto createOrder(CreateOrderCommand command);

    Page<OrderDto> getPagedOrders(OrderFilter filter, Pageable pageable);

    OrderDto getOrderById(Long id);

    OrderDto updateOrder(Long id, CreateOrderCommand command);

    Boolean deleteOrderById(Long id);
}
