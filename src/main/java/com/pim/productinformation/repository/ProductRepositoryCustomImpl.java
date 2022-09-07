package com.pim.productinformation.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    private final EntityManagerFactory emf;

    public ProductRepositoryCustomImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Object[]> findAttributes(Long productId) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager
                .createQuery("SELECT a.id, a.name, av.attributeValue FROM AttributeValue av" +
                        " INNER JOIN Product p ON p.id = av.product.id" +
                        " INNER JOIN Attribute a ON a.id = av.attribute.id AND p.category.id = a.category.id" +
                        " AND p.id=:productId");
        query.setParameter("productId", productId);

        return query.getResultList();
    }
}
