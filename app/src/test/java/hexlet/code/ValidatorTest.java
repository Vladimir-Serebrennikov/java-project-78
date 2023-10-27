package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;;

public class ValidatorTest {
    @Test
    public void testRequiredWithNonNullString() {
        var schema = new StringSchema();
        assertTrue(schema.required().isValid("TestString"));
    }
    @Test
    public void testRequiredWithNullString() {
        var schema = new StringSchema();
        assertFalse(schema.required().isValid(null));
    }
    @Test
    public void testMinLengthWithValidString() {
        var schema = new StringSchema();
        assertTrue(schema.minLength(5).isValid("ValidString"));
    }
    @Test
    public void testMinLengthWithInvalidString() {
        var schema = new StringSchema();
        assertFalse(schema.minLength(10).isValid("Short"));
    }
    @Test
    public void testContainsWithContainedSubstring() {
        var schema = new StringSchema();
        assertTrue(schema.contains("apple").isValid("I love apples"));
    }
    @Test
    public void testContainsWithNonContainedSubstring() {
        var schema = new StringSchema();
        assertFalse(schema.contains("fruits").isValid("I love apples"));
    }
    @Test
    public void testRequiredWithValidValue() {
        var schema = new NumberSchema();
        Object validNumber = 77;
        assertTrue(schema.required().isValid(validNumber));
    }
    @Test
    public void testRequiredWithNullValue() {
        var schema = new NumberSchema();
        assertFalse(schema.required().isValid(null));
    }
    @Test
    public void testRequiredWithNonNumberValue() {
        var schema = new NumberSchema();
        Object nonNumberValue = "String";
        assertFalse(schema.required().isValid(nonNumberValue));
    }
    @Test
    public void testPositiveWithPositiveValue() {
        var schema = new NumberSchema();
        Object positiveNumber = 10;

        assertTrue(schema.positive().isValid(positiveNumber));
    }
    @Test
    public void testPositiveWithNegativeValue() {
        var schema = new NumberSchema();
        Object negativeNumber = -5;

        assertFalse(schema.positive().isValid(negativeNumber));
    }
    @Test
    public void testRangeWithInclusiveValue() {
        var schema = new NumberSchema();
        Object valueInRange = 5;

        assertTrue(schema.range(5, 10).isValid(valueInRange));
    }
    @Test
    public void testRangeWithOutOfRangeValue() {
        var schema = new NumberSchema();
        Object valueOutOfRange = 3;

        assertFalse(schema.range(5, 10).isValid(valueOutOfRange));
    }
    @Test
    public void testRequiredWithValidMap() {
        var schema = new MapSchema();
        Map<String, Integer> validMap = new HashMap<>();
        validMap.put("key1", 1);
        validMap.put("key2", 2);
        assertTrue(schema.required().isValid(validMap));
    }
    @Test
    public void testRequiredWithNullMap() {
        var schema = new MapSchema();
        assertFalse(schema.required().isValid(null));
    }

    @Test
    public void testSizeOfWithValidSize() {
        var schema = new MapSchema();
        Map<String, Integer> validMap = new HashMap<>();
        validMap.put("key1", 1);
        validMap.put("key2", 2);

        assertTrue(schema.sizeof(2).isValid(validMap));
    }

    @Test
    public void testSizeOfWithInvalidSize() {
        var schema = new MapSchema();
        Map<String, Integer> validMap = new HashMap<>();
        validMap.put("key1", 1);
        validMap.put("key2", 2);

        assertFalse(schema.sizeof(3).isValid(validMap));
    }
    @Test
    public void testShapeWithValidMap() {
        var schema = new MapSchema();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("key1", new NumberSchema().positive());
        schemas.put("key2", new StringSchema().minLength(3));
        Map<String, Object> validMap = new HashMap<>();
        validMap.put("key1", 5);
        validMap.put("key2", "abc");

        assertTrue(schema.shape(schemas).isValid(validMap));
    }
    @Test
    public void testShapeWithInvalidMap() {
        var schema = new MapSchema();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("key1", new NumberSchema().positive());
        schemas.put("key2", new StringSchema().minLength(3));
        Map<String, Object> invalidMap = new HashMap<>();
        invalidMap.put("key1", -5);
        invalidMap.put("key2", "ab");

        assertFalse(schema.shape(schemas).isValid(invalidMap));
    }
}
