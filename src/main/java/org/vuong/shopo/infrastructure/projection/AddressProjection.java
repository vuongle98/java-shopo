package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Address;

@Projection(name = "address", types = {Address.class})
public interface AddressProjection {

    Long getId();

    Long getUserId();

    String getAddressLine1();

    String getAddressLine2();

    String getCity();

    String getState();

    String getCountry();

    String getPostalCode();

    String getPhone();
}
