package org.vuong.shopo.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    private Long userId;

    @Column(nullable = false)
    private String currency;

    private String code;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING; // PENDING, PAID, CANCELLED, SHIPPED

    @RestResource(exported = false)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();

    @JsonIgnore
    private Double totalPrice() {
        return orderItems.stream().map(OrderItem::totalPrice).reduce(0d, Double::sum);
    }

    public enum OrderStatus {
        PENDING, CONFIRMED, PAID, SHIPPED, DELIVERED, CANCELLED
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItem.setOrder(null);
        orderItems.remove(orderItem);
    }

}
