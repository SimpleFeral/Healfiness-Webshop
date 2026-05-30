package com.healfiness.backend.outbound.db.jpaentities.products;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "products")
public class ProductEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @Column(columnDefinition = "SERIAL")
    private Long productsId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "categories_id", nullable = false)
    private CategoryEntity category;

    public ProductEntity() {
        super();
    }

    public ProductEntity(
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
            CategoryEntity category
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(productsId, that.productsId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productsId);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productsId=" + productsId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category=" + category +
                "} " + super.toString();
    }
}
