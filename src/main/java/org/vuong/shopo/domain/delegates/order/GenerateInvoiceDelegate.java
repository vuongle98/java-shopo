package org.vuong.shopo.domain.delegates.order;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class GenerateInvoiceDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Generating Invoice for Order Id: " + execution.getVariable("orderId"));

        execution.setVariable("invoiceCode", "invoice-123");
    }
}
