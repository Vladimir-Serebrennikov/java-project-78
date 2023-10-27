package hexlet.code;
import java.util.Map;
import java.util.Objects;
public class MapSchema extends BaseSchema {
    public MapSchema required() {
        addCheck("required",
                value -> Objects.nonNull(value) && value instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof",
                value -> {
                    Map<?, ?> map = (Map<?, ?>) value;
                    return map.size() == size;
                });
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("sizeof",
                value -> schemas.keySet()
                        .stream().allMatch(key -> {
                            Object mapValue = ((Map<?, ?>) value).get(key);
                            return schemas.get(key).isValid(mapValue);
                        }));
        return this;
    }
}
