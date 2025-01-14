package org.vuong.shopo.infrastructure.projection;


import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Product;

@Projection(name = "product", types = Product.class)
public interface ProductProjection {

    Long getId();

    String getName();

    String getSlug();

    String getSku();

    String getDescription();

    Double getPrice();

    Integer getStockQuantity();

    CategoryProjection getCategory();

    VendorProjection getVendor();

    ShopProjection getShop();

}
