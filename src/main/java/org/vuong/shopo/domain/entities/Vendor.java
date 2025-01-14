package org.vuong.shopo.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vuong.shopo.shared.utils.StringUtils;

import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "vendors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String description;

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
