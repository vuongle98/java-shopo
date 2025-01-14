package org.vuong.shopo.infrastructure.specification;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.vuong.shopo.application.queries.CategoryFilter;
import org.vuong.shopo.domain.entities.Category;
import org.vuong.shopo.shared.utils.SqlUtil;

import java.util.ArrayList;
import java.util.List;

public class CategorySpecification {

    public static Specification<Category> withFilter(CategoryFilter filter) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(filter.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), SqlUtil.getLikePattern(filter.getName())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
