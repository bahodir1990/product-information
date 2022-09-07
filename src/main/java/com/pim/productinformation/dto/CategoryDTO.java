package com.pim.productinformation.dto;


import com.sun.istack.NotNull;

import java.util.List;

public class CategoryDTO {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private List<AttributeDTO> attributes;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id,
                       String name,
                       String description,
                       List<AttributeDTO> attributes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attributes = attributes;
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

    public List<AttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDTO> attributes) {
        this.attributes = attributes;
    }
}
