package com.pim.productinformation.repository;

import com.pim.productinformation.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryBasic extends JpaRepository<Product, Long> {
}
