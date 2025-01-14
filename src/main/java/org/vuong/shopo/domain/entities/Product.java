package org.vuong.shopo.domain.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.rest.core.annotation.RestResource;
import org.vuong.shopo.shared.utils.StringUtils;

import java.util.Objects;

@Entity
@Table(
        name = "products",
        indexes = {
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_slug", columnList = "slug"),
                @Index(name = "idx_name_slug", columnList = "name, slug")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE products set deleted_at = NOW() WHERE id = ?")
@SQLRestriction(value = "deleted_at IS NULL")
public class Product extends BaseEntity {

    @RestResource(exported = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    @JsonBackReference("vendor-products")
    private Vendor vendor;

    @RestResource(exported = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @JsonBackReference("shop-products")
    private Shop shop;

    @RestResource(exported = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference("product-items")
    private Category category;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    private Boolean isActive = true;


    protected void onCreate() {
        super.onCreate();
        if (Objects.nonNull(name) && !name.isEmpty()) {
            slug = StringUtils.toSlug(getName());
        }
    }

    protected void onUpdate() {
        super.onUpdate();
        if (Objects.nonNull(name) && !name.isEmpty()) {
            slug = StringUtils.toSlug(name);
        }
    }
}
