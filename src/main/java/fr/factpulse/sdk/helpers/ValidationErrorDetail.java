package fr.factpulse.sdk.helpers;
import java.util.Map;
public class ValidationErrorDetail {
    public String level = "", item = "", reason = "", source, code;
    public ValidationErrorDetail() {}
    public ValidationErrorDetail(String level, String item, String reason, String source, String code) {
        this.level = level != null ? level : "";
        this.item = item != null ? item : "";
        this.reason = reason != null ? reason : "";
        this.source = source;
        this.code = code;
    }
    public String toString() { return "[" + (item.isEmpty() ? "unknown" : item) + "] " + (reason.isEmpty() ? "Unknown error" : reason); }
    public static ValidationErrorDetail fromMap(Map<String, Object> m) {
        ValidationErrorDetail d = new ValidationErrorDetail();
        if (m.containsKey("level")) d.level = (String)m.get("level");
        if (m.containsKey("item")) d.item = (String)m.get("item");
        if (m.containsKey("reason")) d.reason = (String)m.get("reason");
        if (m.containsKey("source")) d.source = (String)m.get("source");
        if (m.containsKey("code")) d.code = (String)m.get("code");
        return d;
    }
}
