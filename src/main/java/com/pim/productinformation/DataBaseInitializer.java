package com.pim.productinformation;

import com.pim.productinformation.model.*;
import com.pim.productinformation.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataBaseInitializer implements CommandLineRunner {

    private final AttributeRepository attributeRepository;

    private final AttributeValueRepository valueRepository;

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public DataBaseInitializer(AttributeRepository attributeRepository,
                               AttributeValueRepository valueRepository,
                               CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.attributeRepository = attributeRepository;
        this.valueRepository = valueRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        this.attributeRepository.deleteAll();
        this.valueRepository.deleteAll();
        this.categoryRepository.deleteAll();
        this.productRepository.deleteAll();

        Category category1 = new Category(
                "Clothes",
                "This category includes suits, shirts, pants, pullovers, coats, etc.",
                LocalDate.now(),
                LocalDate.now()
        );

        Category category2 = new Category(
                "Shoes",
                "This category includes boots, sneakers, sandals, etc.",
                LocalDate.now(),
                LocalDate.now()
        );

        this.categoryRepository.saveAll(List.of(category1, category2));

        Product product1 = new Product(
                "T-shirt",
                "The New Vintage T-Shirts",
                category1,
                "sku1",
                new BigDecimal(25),
                100,
                LocalDate.now(),
                LocalDate.now()
        );

        Product product2 = new Product(
                "Jeans",
                "Levis 510 jeans are cut to a skinny fit with a medium leg opening",
                category1,
                "sku2",
                new BigDecimal(75),
                300,
                LocalDate.now(),
                LocalDate.now()
        );

        this.productRepository.saveAll(List.of(product1, product2));

        Attribute attribute1 = new Attribute(
                "color",
                "Attribute refers to the color of the product",
                category1
        );

        Attribute attribute2 = new Attribute(
                "size",
                "Attribute refers to the size of the product",
                category1
        );

        this.attributeRepository.saveAll(List.of(attribute1, attribute2));

        AttributeValue value1 = new AttributeValue(
                "M",
                attribute2,
                product1
        );

        AttributeValue value2 = new AttributeValue(
                "red",
                attribute1,
                product1
        );

        this.valueRepository.saveAll(List.of(value1, value2));

    }
}
