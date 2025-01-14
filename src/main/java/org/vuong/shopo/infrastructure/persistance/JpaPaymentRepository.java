package org.vuong.shopo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.vuong.shopo.domain.entities.Payment;
import org.vuong.shopo.domain.repositories.PaymentRepository;
import org.vuong.shopo.infrastructure.projection.PaymentProjection;

@RepositoryRestResource(excerptProjection = PaymentProjection.class)
public interface JpaPaymentRepository extends JpaRepository<Payment, Long>, PaymentRepository {
}
