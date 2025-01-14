package org.vuong.shopo.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;
import org.vuong.shopo.shared.utils.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @RestResource(exported = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference("sub-categories")
    private Category parent;

    @RestResource(exported = false)
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @JsonManagedReference("sub-categories")
    private List<Category> subcategories;

    @RestResource(exported = false)
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonManagedReference("product-items")
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
