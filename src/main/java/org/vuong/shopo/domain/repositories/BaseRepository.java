package org.vuong.shopo.domain.repositories;


public interface BaseRepository<T> {

    T save(T entity);

    void delete(T entity);

    void deleteById(Long id);
}
