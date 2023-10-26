package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
public abstract class Schema {
    private final Map<String, Predicate<Object>> checks;

    public Schema() {
        this.checks = new HashMap<>();
    }

    public void addCheck(String nameSchema, Predicate<Object> check) {
        checks.put(nameSchema, check);
    }

    public boolean isValid(Object obj) {
        return checks.values()
                .stream()
                .allMatch(check -> check.test(obj));
    }
}