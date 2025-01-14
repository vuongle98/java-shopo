package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.Shipment;
import org.vuong.shopo.domain.repositories.ShipmentRepository;
import org.vuong.shopo.infrastructure.projection.ShipmentProjection;

@RepositoryRestResource(excerptProjection = ShipmentProjection.class)
public interface JpaShipmentRepository extends JpaRepository<Shipment, Long>, ShipmentRepository {
}
