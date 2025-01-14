package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.Address;
import org.vuong.shopo.domain.repositories.AddressRepository;
import org.vuong.shopo.infrastructure.projection.AddressProjection;

@RepositoryRestResource(excerptProjection = AddressProjection.class)
public interface JpaAddressRepository extends JpaRepository<Address, Long>, AddressRepository {
}
