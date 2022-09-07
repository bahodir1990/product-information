package com.pim.productinformation.dto;

import com.sun.istack.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {
    private Long id;

    @NotNull
    private String name;

    private String description;

    private ProductCategoryDTO category;

    private List<ProductAttributesDTO> attributes;

    private String sku;

    private BigDecimal price;

    @NotNull
    private Integer unitsInStock;

    public ProductDTO() {
    }

    public ProductDTO(String name,
                      String description,
                      String sku,
                      BigDecimal price,
                      Integer unitsInStock) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.unitsInStock = unitsInStock;
    }

    public ProductDTO(String name,
                      String description,
                      ProductCategoryDTO category,
                      List<ProductAttributesDTO> attributes,
                      String sku,
                      BigDecimal price,
                      Integer unitsInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.attributes = attributes;
        this.sku = sku;
        this.price = price;
        this.unitsInStock = unitsInStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryDTO category) {
        this.category = category;
    }

    public List<ProductAttributesDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttributesDTO> attributes) {
        this.attributes = attributes;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }
}
