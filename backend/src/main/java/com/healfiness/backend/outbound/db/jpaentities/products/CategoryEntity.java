package com.healfiness.backend.outbound.db.jpaentities.products;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class CategoryEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @Column(columnDefinition = "SERIAL")
    private Long categoriesId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products;

    public CategoryEntity() {
        super();
    }

    public CategoryEntity(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long categoriesId,
            String name,
            String description,
            List<ProductEntity> products
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.categoriesId = categoriesId;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(categoriesId, that.categoriesId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(categoriesId);
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "categoriesId=" + categoriesId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                "} " + super.toString();
    }
}
