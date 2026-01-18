package fr.factpulse.sdk.helpers;

import java.util.ArrayList;
import java.util.List;

public class FactPulseException extends RuntimeException {
    public final int statusCode;
    public final List<Object> details;

    public FactPulseException(String message) {
        this(message, 0, new ArrayList<>());
    }

    public FactPulseException(String message, int statusCode) {
        this(message, statusCode, new ArrayList<>());
    }

    public FactPulseException(String message, int statusCode, List<Object> details) {
        super(message);
        this.statusCode = statusCode;
        this.details = details != null ? details : new ArrayList<>();
    }
}
