package com.pim.productinformation.dto;

public class ProductAttributesDTO {

    private Long attributeId;

    private String attributeName;

    private String attributeValue;

    public ProductAttributesDTO() {
    }

    public ProductAttributesDTO(Long attributeId,
                                String attributeName,
                                String attributeValue) {
        this.attributeId = attributeId;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }
}
