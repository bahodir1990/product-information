package com.pim.productinformation.repository;

import com.pim.productinformation.model.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("category")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
