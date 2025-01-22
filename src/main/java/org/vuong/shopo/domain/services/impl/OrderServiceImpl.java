package org.vuong.shopo.domain.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.vuong.shopo.application.commands.CreateOrderCommand;
import org.vuong.shopo.application.commands.CreateOrderItemCommand;
import org.vuong.shopo.application.dto.OrderDto;
import org.vuong.shopo.application.dto.UserDto;
import org.vuong.shopo.application.queries.OrderFilter;
import org.vuong.shopo.domain.entities.Order;
import org.vuong.shopo.domain.entities.OrderItem;
import org.vuong.shopo.domain.entities.Product;
import org.vuong.shopo.domain.repositories.OrderItemRepository;
import org.vuong.shopo.domain.repositories.OrderRepository;
import org.vuong.shopo.domain.repositories.ProductRepository;
import org.vuong.shopo.domain.services.OrderService;
import org.vuong.shopo.infrastructure.services.UserService;
import org.vuong.shopo.infrastructure.specification.OrderSpecification;
import org.vuong.shopo.shared.utils.ObjectData;

import java.util.Objects;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserService userService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public OrderDto createOrder(CreateOrderCommand command) {

        Order order = new Order();
        order.setCurrency(command.getCurrency());
        order.setUserId(command.getUserId());

        addOrderItems(command, order);

        return ObjectData.mapTo(orderRepository.save(order), OrderDto.class);
    }

    @Override
    public Page<OrderDto> getPagedOrders(OrderFilter filter, Pageable pageable) {

        Specification<Order> specification = OrderSpecification.withFilter(filter);
        Page<Order> orders = orderRepository.findAll(specification, pageable);

        return orders.map(o -> ObjectData.mapTo(o, OrderDto.class));
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return findOrderById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public <T> T getOrderById(Long id, Class<T> classType) {
        return findOrderById(id, classType).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public OrderDto updateOrder(Long id, CreateOrderCommand command) {
        Order order = getOrderById(id, Order.class);

        UserDto user = userService.getCurrentUserInfo();

        if (!order.getUserId().equals(user.getId()) || !user.isAdmin()) {
            throw new RuntimeException("Order does not belong to current user");
        }

        if (Objects.nonNull(command.getCurrency())) {
            order.setCurrency(command.getCurrency());
        }

        if (Objects.nonNull(command.getOrderItems())) {
            addOrderItems(command, order);
        }

        if (Objects.nonNull(command.getStatus())) {

            if (command.getStatus().equals(Order.OrderStatus.CONFIRMED)) {
                if (!order.getStatus().equals(Order.OrderStatus.PENDING)) {
                    throw new RuntimeException("Order status is not valid to confirm");
                }
            }

            order.setStatus(command.getStatus());
        }

        return ObjectData.mapTo(orderRepository.save(order), OrderDto.class);
    }

    @Override
    public Boolean deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        return true;
    }

    public Optional<OrderDto> findOrderById(Long id) {
        return findOrderById(id, OrderDto.class);
    }

    public <T> Optional<T> findOrderById(Long id, Class<T> classType) {
        return orderRepository.findById(id).map(o -> ObjectData.mapTo(o, classType));
    }

    private void addOrderItems(CreateOrderCommand command, Order order) {
        for (CreateOrderItemCommand itemCommand : command.getOrderItems()) {
            Product product = productRepository.findById(itemCommand.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemCommand.getQuantity());
            orderItem.setPrice(product.getPrice());

            order.addOrderItem(orderItem);
        }
    }

}
