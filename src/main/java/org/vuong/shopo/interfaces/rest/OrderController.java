package org.vuong.shopo.interfaces.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.vuong.shopo.application.commands.CreateOrderCommand;
import org.vuong.shopo.application.dto.OrderDto;
import org.vuong.shopo.application.queries.OrderFilter;
import org.vuong.shopo.domain.services.OrderService;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<OrderDto>> findAll(OrderFilter filter, Pageable pageable) {

        return ResponseEntity.ok(orderService.getPagedOrders(filter, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(
            @PathVariable Long id,
            @RequestBody @Validated CreateOrderCommand command
    ) {
        return ResponseEntity.ok(orderService.updateOrder(id, command));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(
            @RequestBody @Validated CreateOrderCommand command
    ) {
        return ResponseEntity.ok(orderService.createOrder(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
