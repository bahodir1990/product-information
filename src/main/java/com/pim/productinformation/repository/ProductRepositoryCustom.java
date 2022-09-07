package com.pim.productinformation.repository;

import java.util.List;

public interface ProductRepositoryCustom {
    public List<Object[]> findAttributes(Long productId);
}
