package com.pim.productinformation.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    T update(T object, Long id);

    T findById(Long id);

    List<T> findAll();

    void deleteById(Long id);
}
