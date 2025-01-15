package org.vuong.shopo.infrastructure.services;

import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CamundaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamundaService.class);

    private final RuntimeService runtimeService;

    public CamundaService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void startProcess(String processKey, String businessKey, Map<String, Object> variables) {
        LOGGER.info("Starting Camunda process: key={}, businessKey={}, variables={}", processKey, businessKey, variables);
        runtimeService.startProcessInstanceByKey(processKey, businessKey, variables);
    }
}
