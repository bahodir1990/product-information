package com.pim.productinformation.repository;

import com.pim.productinformation.model.Attribute;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("attribute")
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
