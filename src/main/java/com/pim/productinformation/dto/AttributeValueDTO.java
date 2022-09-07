package com.pim.productinformation.dto;

import com.sun.istack.NotNull;

public class AttributeValueDTO {

    private Long id;

    @NotNull
    private String attributeValue;

    public AttributeValueDTO() {
    }

    public AttributeValueDTO(Long id, String attributeValue) {
        this.id = id;
        this.attributeValue = attributeValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
