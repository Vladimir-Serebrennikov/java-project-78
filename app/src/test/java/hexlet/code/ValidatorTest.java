package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;;

public class ValidatorTest {
    @Test
    public void testStringValidator() {
        var schema = new StringSchema();
        assertTrue(schema.required().isValid("TestString"));
        assertFalse(schema.required().isValid(null));
        assertTrue(schema.minLength(5).isValid("ValidString"));
        assertFalse(schema.minLength(10).isValid("Short"));
        assertTrue(schema.contains("apple").isValid("I love apples"));
        assertFalse(schema.contains("fruits").isValid("I love apples"));
    }

    @Test
    public void testNumberValidator() {
        var schema = new NumberSchema();
        Object validNumber = 77;
        Object nonNumberValue = "String";
        Object positiveNumber = 10;
        Object negativeNumber = -5;
        Object valueInRange = 5;
        Object valueOutOfRange = 3;
        assertTrue(schema.required().isValid(validNumber));
        assertFalse(schema.required().isValid(null));
        assertFalse(schema.required().isValid(nonNumberValue));
        assertTrue(schema.positive().isValid(positiveNumber));
        assertFalse(schema.positive().isValid(negativeNumber));
        assertTrue(schema.range(5, 10).isValid(valueInRange));
        assertFalse(schema.range(5, 10).isValid(valueOutOfRange));
    }

    @Test
    public void testMapValidator() {
        var schema = new MapSchema();
        assertFalse(schema.required().isValid(null));
        Map<String, Object> validMap = new HashMap<>();
        validMap.put("key1", 1);
        validMap.put("key2", 2);
        assertTrue(schema.required().isValid(validMap));
        assertTrue(schema.sizeof(2).isValid(validMap));

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("key1", new NumberSchema().positive());
        schemas.put("key2", new StringSchema().minLength(3));
        validMap.put("key1", 5);
        validMap.put("key2", "abc");

        assertTrue(schema.shape(schemas).isValid(validMap));

        validMap.put("key1", -5);
        validMap.put("key2", "ab");

        assertFalse(schema.shape(schemas).isValid(validMap));

    }
}
