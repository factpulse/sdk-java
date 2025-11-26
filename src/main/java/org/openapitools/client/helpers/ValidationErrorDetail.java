package org.openapitools.client.helpers;

import java.util.Map;

public class ValidationErrorDetail {
    public String level = "", item = "", reason = "", source, code;
    public String toString() { return String.format("[%s] %s", item.isEmpty() ? "unknown" : item, reason.isEmpty() ? "Unknown error" : reason); }
    public static ValidationErrorDetail fromMap(Map<String, Object> map) {
        ValidationErrorDetail d = new ValidationErrorDetail();
        if (map.containsKey("level")) d.level = (String) map.get("level");
        if (map.containsKey("item")) d.item = (String) map.get("item");
        if (map.containsKey("reason")) d.reason = (String) map.get("reason");
        if (map.containsKey("source")) d.source = (String) map.get("source");
        if (map.containsKey("code")) d.code = (String) map.get("code");
        return d;
    }
}
