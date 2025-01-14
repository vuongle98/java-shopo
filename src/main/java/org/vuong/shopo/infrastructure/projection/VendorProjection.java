package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Vendor;

@Projection(name = "vendor", types = {Vendor.class})
public interface VendorProjection {

    Long getId();

    String getName();

    String getDescription();

    String getSlug();
}
