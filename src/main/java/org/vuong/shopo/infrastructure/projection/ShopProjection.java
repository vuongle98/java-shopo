package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Shop;

@Projection(name = "shop", types = {Shop.class})
public interface ShopProjection {

    Long getId();

    String getName();

    String getSlug();

    String getDescription();

//    List<ProductProjection> getProducts();
}
