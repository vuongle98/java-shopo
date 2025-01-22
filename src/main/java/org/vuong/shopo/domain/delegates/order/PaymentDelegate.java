package org.vuong.shopo.domain.delegates.order;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class PaymentDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");
        Integer totalAmount = (Integer) execution.getVariable("totalAmount");
        String paymentMethod = (String) execution.getVariable("paymentMethod");

        System.out.println("Processing payment for order: " + orderId);
        System.out.println("Payment Method: " + paymentMethod + ", Total Amount: $" + totalAmount);

        // Mock payment logic
        boolean paymentSuccess = processPayment(orderId, totalAmount, paymentMethod);

        if (!paymentSuccess) {
            throw new BpmnError("PAYMENT_FAILED", "Payment failed for order: " + orderId);
        }

        execution.setVariable("paymentStatus", "SUCCESS");
    }

    private boolean processPayment(String orderId, double totalAmount, String paymentMethod) {
        // Simulated payment logic (mock payment success)
        System.out.println("Simulating payment processing...");
        return true; // Assume payment is always successful for now
    }
}
