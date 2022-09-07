package com.pim.productinformation.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("product")
@Repository
public interface ProductRepository extends ProductRepositoryBasic, ProductRepositoryCustom {
}
