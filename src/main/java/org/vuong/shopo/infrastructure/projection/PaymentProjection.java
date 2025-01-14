package org.vuong.shopo.infrastructure.projection;

import org.springframework.data.rest.core.config.Projection;
import org.vuong.shopo.domain.entities.Payment;

@Projection(name = "payment", types = {Payment.class})
public interface PaymentProjection {

    Long getId();

    OrderProjection getOrder();

    Payment.PaymentStatus getStatus();

    String getMethod();
}
