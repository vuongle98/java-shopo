package org.vuong.shopo.infrastructure.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> {

    public static <T> Specification<T> withFilter(Filter<T> filter, EntityField<T>... fields) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (EntityField<T> field : fields) {
                field.getFilterPredicate(filter, root, criteriaBuilder, predicates);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public interface EntityField<T> {
        void getFilterPredicate(Filter<T> filter, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates);
    }

    public record Filter<T>(String name, Integer minPrice, Integer maxPrice) {
    }

}
