package com.example.advertismentService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<E, I> {

    Page<E> findAll(Pageable pageable, List<String> status);

    E save(E entity);

    E updateById(E entity);

    E findById(I id);

    void deleteById(I id);
}
