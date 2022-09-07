package com.pim.productinformation.dto;

import com.sun.istack.NotNull;

public class ProductCategoryDTO {
    private Long id;

    @NotNull
    private String name;

    private String description;

    public ProductCategoryDTO() {
    }

    public ProductCategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
