package com.healfiness.backend.core.domain.entities.products;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;

import java.time.Instant;
import java.util.List;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.products.CategoryEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class Category extends AbstractObjectMetaData {

    private Long categoriesId;

    private String name;

    private String description;

    private List<Product> products;

    public Category() {
        super();
    }

    public Category(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long categoriesId,
            String name,
            String description,
            List<Product> products
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoriesId=" + categoriesId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                "} " + super.toString();
    }
}
