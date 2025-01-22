package org.vuong.shopo.domain.delegates.order;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateOrderDelegate implements JavaDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateOrderDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");

        System.out.println("Validating order for orderId: " + orderId);


        execution.setVariable("orderValid", true);
        execution.setVariable("orderId", orderId);
        execution.setVariable("totalAmount", 10);
        execution.setVariable("paymentMethod", "CASH");

        String[] orderIdParts = orderId.split("-");
        Integer orderNumber = Integer.valueOf(orderIdParts[orderIdParts.length - 1]);

        if (orderNumber % 2 == 0) {
            execution.setVariable("notifyCode", "STOCK_UNAVAILABLE");
            throw new BpmnError("STOCK_UNAVAILABLE");
        }
    }
}
