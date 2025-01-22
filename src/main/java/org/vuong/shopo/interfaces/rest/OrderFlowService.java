package org.vuong.shopo.interfaces.rest;

import org.springframework.stereotype.Service;
import org.vuong.shopo.infrastructure.services.CamundaService;

@Service
public class OrderFlowService {


    private final CamundaService camundaService;

    public OrderFlowService(CamundaService camundaService) {
        this.camundaService = camundaService;
    }

    public void deployOrderFlow() {

    }


}
