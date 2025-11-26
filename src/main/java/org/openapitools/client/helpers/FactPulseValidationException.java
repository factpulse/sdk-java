package org.openapitools.client.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FactPulseValidationException extends FactPulseException {
    public final List<ValidationErrorDetail> errors;
    public FactPulseValidationException(String message) { this(message, new ArrayList<>()); }
    public FactPulseValidationException(String message, List<ValidationErrorDetail> errors) {
        super(errors.isEmpty() ? message : message + "\n\nDétails:\n" + errors.stream().map(e -> "  - " + e).collect(Collectors.joining("\n")));
        this.errors = errors != null ? errors : new ArrayList<>();
    }
}
