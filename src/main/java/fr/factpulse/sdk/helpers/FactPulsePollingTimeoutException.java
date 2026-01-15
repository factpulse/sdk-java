package fr.factpulse.sdk.helpers;
public class FactPulsePollingTimeoutException extends FactPulseException {
    public final String taskId; public final long timeout;
    public FactPulsePollingTimeoutException(String taskId, long timeout) {
        super(String.format("Timeout (%dms) pour tâche %s", timeout, taskId)); this.taskId = taskId; this.timeout = timeout;
    }
}
