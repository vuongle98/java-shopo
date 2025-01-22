package org.vuong.shopo.infrastructure.services;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CamundaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamundaService.class);

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    private final HistoryService historyService;

    private final ManagementService managementService;

    public CamundaService(
            RuntimeService runtimeService,
            TaskService taskService,
            HistoryService historyService,
            ManagementService managementService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.historyService = historyService;
        this.managementService = managementService;
    }

    /**
     * Assign a task to a specific user.
     * @param taskId The ID of the task.
     * @param userId The ID of the user to assign the task to.
     */
    public void assignTask(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
    }

    /**
     * Delegate a task to another user.
     * @param taskId The ID of the task.
     * @param userId The ID of the user to delegate the task to.
     */
    public void delegateTask(String taskId, String userId) {
        taskService.delegateTask(taskId, userId);
    }

    /**
     * Claim a task for a specific user.
     * @param taskId The ID of the task.
     * @param userId The ID of the user claiming the task.
     */
    public void claimTask(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    /**
     * Unclaim a task (release it).
     * @param taskId The ID of the task to unclaim.
     */
    public void unClaimTask(String taskId) {
        taskService.setAssignee(taskId, null);
    }

    /**
     * Retrieve tasks with advanced filtering.
     * @param assignee Filter tasks by assignee.
     * @param candidateGroup Filter tasks by candidate group.
     * @param variables Filter tasks by process variables.
     * @return A list of tasks matching the filters.
     */
    public List<Task> getFilteredTasks(String assignee, String candidateGroup, Map<String, Object> variables) {
        TaskQuery query = taskService.createTaskQuery();
        if (assignee != null) query.taskAssignee(assignee);
        if (candidateGroup != null) query.taskCandidateGroup(candidateGroup);
        if (variables != null) {
            variables.forEach(query::processVariableValueEquals);
        }
        return query.list();
    }

    /**
     * Terminate a running process instance.
     * @param processInstanceId The ID of the process instance to terminate.
     * @param reason The reason for termination.
     */
    public void terminateProcessInstance(String processInstanceId, String reason) {
        runtimeService.deleteProcessInstance(processInstanceId, reason);
    }

    /**
     * Broadcast a message to processes waiting for a specific message event.
     * @param messageName The name of the message event.
     * @param variables Variables to pass with the message.
     */
    public void broadcastMessage(String messageName, Map<String, Object> variables) {
        runtimeService.createMessageCorrelation(messageName)
                .setVariables(variables)
                .correlateAll();
    }

    /**
     * Retry a failed job.
     * @param jobId The ID of the failed job.
     */
    public void retryFailedJob(String jobId) {
        managementService.setJobRetries(jobId, 1);
    }

    /**
     * Retrieve all failed jobs.
     * @return A list of failed jobs.
     */
    public List<Job> getFailedJobs() {
        return managementService.createJobQuery()
                .withException()
                .list();
    }

    /**
     * Update process variables for a running process instance.
     * @param processInstanceId The ID of the process instance.
     * @param variables Variables to update.
     */
    public void updateProcessVariables(String processInstanceId, Map<String, Object> variables) {
        runtimeService.setVariables(processInstanceId, variables);
    }

    /**
     * Retrieve historic process instances with filters.
     * @param processDefinitionKey Filter by process definition key.
     * @param startedBy Filter by the user who started the process.
     * @param finished Only return completed instances.
     * @return A list of HistoricProcessInstances matching the filters.
     */
    public List<HistoricProcessInstance> getHistoricProcessInstances(String processDefinitionKey, String startedBy, boolean finished) {
        var query = historyService.createHistoricProcessInstanceQuery();
        if (processDefinitionKey != null) query.processDefinitionKey(processDefinitionKey);
        if (startedBy != null) query.startedBy(startedBy);
        if (finished) query.finished();
        return query.list();
    }

    /**
     * Set task priority.
     * @param taskId The ID of the task.
     * @param priority The priority to set.
     */
    public void setTaskPriority(String taskId, int priority) {
        taskService.setPriority(taskId, priority);
    }

    /**
     * Get task priority.
     * @param taskId The ID of the task.
     * @return The task priority.
     */
    public int getTaskPriority(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new IllegalArgumentException("Task not found with ID: " + taskId);
        }
        return task.getPriority();
    }

    /**
     * Retrieve active event subscriptions for a process instance.
     * @param processInstanceId The ID of the process instance.
     * @return A list of EventSubscriptions.
     */
    public List<EventSubscription> getEventSubscriptions(String processInstanceId) {
        return runtimeService.createEventSubscriptionQuery()
                .processInstanceId(processInstanceId)
                .list();
    }

    /**
     * Batch complete tasks.
     * @param taskIds List of task IDs to complete.
     * @param variables Variables to pass when completing tasks.
     */
    public void batchCompleteTasks(List<String> taskIds, Map<String, Object> variables) {
        for (String taskId : taskIds) {
            taskService.complete(taskId, variables);
        }
    }

    /**
     * Retrieve a snapshot of process variables for debugging or auditing.
     * @param processInstanceId The ID of the process instance.
     * @return A map of process variables.
     */
    public Map<String, Object> getProcessVariableSnapshot(String processInstanceId) {
        return runtimeService.getVariables(processInstanceId);
    }

    /**
     * Retrieve jobs stuck in the dead letter queue.
     * @return A list of jobs in the dead letter queue.
     */
    public List<Job> getDeadLetterJobs() {
        return managementService.createJobQuery()
                .withRetriesLeft()
                .list();
    }

    /**
     * Retry jobs stuck in the dead letter queue.
     * @param jobId The ID of the dead letter job.
     */
    public void retryDeadLetterJob(String jobId) {
        managementService.setJobRetries(jobId, 1);
    }

    /**
     * Suspend a job.
     * @param jobId The ID of the job to suspend.
     */
    public void suspendJob(String jobId) {
        managementService.suspendJobById(jobId);
    }

    /**
     * Activate a suspended job.
     * @param jobId The ID of the suspended job.
     */
    public void activateJob(String jobId) {
        managementService.activateJobById(jobId);
    }

    /**
     * Retrieve embedded forms associated with a user task.
     * @param taskId The ID of the task.
     * @return The form key.
     */
    public String getEmbeddedForm(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new IllegalArgumentException("Task not found with ID: " + taskId);
        }
        return task.getFormKey();
    }
}