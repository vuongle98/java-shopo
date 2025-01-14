package org.vuong.shopo.domain.repositories;

import org.vuong.shopo.domain.entities.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long id);
}
