package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.Vendor;
import org.vuong.shopo.domain.repositories.VendorRepository;
import org.vuong.shopo.infrastructure.projection.VendorProjection;

@RepositoryRestResource(excerptProjection = VendorProjection.class)
public interface JpaVendorRepository extends JpaRepository<Vendor, Long>, VendorRepository {
}
