package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
public abstract class BaseSchema {
    private final Map<String, Predicate<Object>> checks;

    public BaseSchema() {
        this.checks = new HashMap<>();
    }

/*
 * Adds a new validation rule to the schema. This method is intended to be overridden
 * in subclasses to add custom validation rules.
 */
    public void addCheck(String nameSchema, Predicate<Object> check) {
        checks.put(nameSchema, check);
    }

    public boolean isValid(Object obj) {
        return checks.values()
                .stream()
                .allMatch(check -> check.test(obj));
    }
}
