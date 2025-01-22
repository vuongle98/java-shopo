package org.vuong.shopo.domain.delegates.order;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class NotifyCustomerDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");
        String notifyCode = (String) execution.getVariable("notifyCode");
        String stockUnavailable = (String) execution.getVariable("STOCK_UNAVAILABLE");
        System.out.println("Sending notification for order: " + orderId + " notify code: " + notifyCode);
        System.out.println("Stock unavailable: " + stockUnavailable);

        // Mock notification
        execution.setVariable("notificationStatus", "SENT");
        execution.setVariable("orderId", orderId);
    }
}
