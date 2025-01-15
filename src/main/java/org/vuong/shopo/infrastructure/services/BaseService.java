package org.vuong.shopo.infrastructure.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    Optional<T> findById(Long id);
    List<T> findAll();


}
