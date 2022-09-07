package com.pim.productinformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "attributes")
public class Attribute {

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "attribute_sequence",
            sequenceName = "attribute_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "attribute_sequence")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @JsonManagedReference(value = "attribute")
    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<AttributeValue> attributeValues;

    @JsonBackReference(value = "category")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    public Attribute() {
    }

    public Attribute(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
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

    @JsonBackReference
    public List<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    @JsonBackReference
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;
        Attribute attribute = (Attribute) o;
        return Objects.equals(getId(), attribute.getId()) &&
                Objects.equals(getName(), attribute.getName()) &&
                Objects.equals(getDescription(), attribute.getDescription()) &&
                Objects.equals(getAttributeValues(), attribute.getAttributeValues()) &&
                Objects.equals(getCategory(), attribute.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(),
                getAttributeValues(), getCategory());
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", attributeValues=" + attributeValues +
                ", category=" + category +
                '}';
    }
}
