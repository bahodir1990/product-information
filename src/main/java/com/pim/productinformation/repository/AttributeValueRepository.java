package com.pim.productinformation.repository;

import com.pim.productinformation.model.AttributeValue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("attribute_value")
@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
}
