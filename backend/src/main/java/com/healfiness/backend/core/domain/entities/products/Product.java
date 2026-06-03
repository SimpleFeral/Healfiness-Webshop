package com.healfiness.backend.core.domain.entities.products;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.products.ProductEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class Product extends AbstractObjectMetaData {

    private Long productsId;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stockQuantity;

    private Category category;

    public Product() {
        super();
    }

    public Product(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long productsId,
            String name,
            String description,
            BigDecimal price,
            Integer stockQuantity
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.productsId = productsId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Product(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long productsId,
            String name,
            String description,
            BigDecimal price,
            Integer stockQuantity,
            Category category
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.productsId = productsId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return Objects.equals(productsId, that.productsId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productsId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productsId=" + productsId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category=" + category +
                "} " + super.toString();
    }
}
