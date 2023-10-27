package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        addCheck("required",
                value -> Objects.nonNull(value) && value instanceof Number);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive",
                value -> value == null || ((int) value) > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        addCheck("range",
                value -> value == null || (int) value >= start && (int) value <= end);
        return this;
    }



}
