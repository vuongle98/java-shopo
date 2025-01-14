package org.vuong.shopo.infrastructure.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.vuong.shopo.application.queries.OrderFilter;
import org.vuong.shopo.domain.entities.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderSpecification {

    public static Specification<Order> withFilter(OrderFilter filter) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(filter.getOrderId())) {
                predicates.add(criteriaBuilder.equal(root.get("name"), filter.getOrderId()));
            }

            if (Objects.nonNull(filter.getStatus())) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
