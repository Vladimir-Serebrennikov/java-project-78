package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema extends BaseSchema {
    public StringSchema required() {
        addCheck("required",
                value -> Objects.nonNull(value) && !value.toString().isEmpty() && value instanceof String);
        return this;
    }

    public StringSchema minLength(int min) {
        addCheck("minLength",
                value -> value.toString().length() >= min);
        return this;
    }

    public StringSchema contains(String word) {
        addCheck("contains",
                value -> value.toString().contains(word));
        return this;
    }



}
