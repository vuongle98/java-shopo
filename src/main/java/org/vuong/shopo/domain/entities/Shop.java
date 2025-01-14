package org.vuong.shopo.domain.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;
import org.vuong.shopo.shared.utils.StringUtils;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String slug;

    @RestResource(exported = false)
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @JsonManagedReference("shop-products")
    private List<Product> products;

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
