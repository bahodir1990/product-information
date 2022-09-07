package com.pim.productinformation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_sequence")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @JsonBackReference(value = "category")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @JsonManagedReference(value="product")
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<AttributeValue> attributeValues;

    private String sku;

    private BigDecimal price;

    @Column(nullable = false)
    private Integer unitsInStock;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    public Product() {
    }

    public Product(String name,
                   String description,
                   Category category,
                   String sku,
                   BigDecimal price,
                   Integer unitsInStock,
                   LocalDate createdDate,
                   LocalDate updatedDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.sku = sku;
        this.price = price;
        this.unitsInStock = unitsInStock;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonBackReference(value="product")
    public List<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                getCategory().equals(product.getCategory()) &&
                Objects.equals(getAttributeValues(), product.getAttributeValues()) &&
                Objects.equals(getSku(), product.getSku()) &&
                getPrice().equals(product.getPrice()) &&
                getUnitsInStock().equals(product.getUnitsInStock()) &&
                Objects.equals(getCreatedDate(), product.getCreatedDate()) &&
                Objects.equals(getUpdatedDate(), product.getUpdatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getName(),
                getDescription(),
                getCategory(),
                getAttributeValues(),
                getSku(),
                getPrice(),
                getUnitsInStock(),
                getCreatedDate(),
                getUpdatedDate());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", attributeValues=" + attributeValues +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", unitsInStock=" + unitsInStock +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
