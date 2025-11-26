package org.openapitools.client.helpers;
import java.util.ArrayList; import java.util.List; import java.util.stream.Collectors;
public class FactPulseValidationException extends FactPulseException {
    public final List<ValidationErrorDetail> errors;
    public FactPulseValidationException(String msg) { this(msg, new ArrayList<>()); }
    public FactPulseValidationException(String msg, List<ValidationErrorDetail> errors) {
        super(errors.isEmpty() ? msg : msg + "\n\nDétails:\n" + errors.stream().map(e -> "  - " + e).collect(Collectors.joining("\n")));
        this.errors = errors != null ? errors : new ArrayList<>();
    }
}
