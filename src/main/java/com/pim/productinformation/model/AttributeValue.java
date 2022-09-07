package com.pim.productinformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "attribute_values")
public class AttributeValue {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "attribute_values_sequence",
            sequenceName = "attribute_values_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "attribute_values_sequence")
    private Long id;

    @Column(nullable = false)
    private String attributeValue;

    @JsonBackReference(value =  "attribute")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", referencedColumnName = "id")
    private Attribute attribute;

    @JsonBackReference(value = "product")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public AttributeValue() {
    }

    public AttributeValue(String attributeValue,
                          Attribute attribute,
                          Product product) {
        this.attributeValue = attributeValue;
        this.attribute = attribute;
        this.product = product;
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

    @JsonBackReference
    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @JsonBackReference
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttributeValue)) return false;
        AttributeValue that = (AttributeValue) o;
        return getId().equals(that.getId()) &&
                getAttributeValue().equals(that.getAttributeValue()) &&
                getAttribute().equals(that.getAttribute()) &&
                getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAttributeValue(), getAttribute(), getProduct());
    }

    @Override
    public String toString() {
        return "AttributeValue{" +
                "id=" + id +
                ", attributeValue='" + attributeValue + '\'' +
                ", attribute=" + attribute +
                ", product=" + product +
                '}';
    }
}
