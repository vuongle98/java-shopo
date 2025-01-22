package org.vuong.shopo.domain.delegates.order;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShipOrderDelegate implements JavaDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipOrderDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Shipping order for orderId: " + execution.getVariable("orderId"));
        System.out.println("Invoice code: " + execution.getVariable("invoiceCode"));
        // Add shipping logic here
        execution.setVariable("orderShipped", true);
        execution.setVariable("notifyCode", "SHIPPED_ORDER");
    }
}
