package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Shipment;

import java.time.LocalDateTime;

@Projection(name = "shipment", types = {Shipment.class})
public interface ShipmentProjection {

    Long getId();

    OrderProjection getOrder();

    String getTrackingNumber();

    LocalDateTime getShipmentDate();

    LocalDateTime getDeliveryDate();

}
