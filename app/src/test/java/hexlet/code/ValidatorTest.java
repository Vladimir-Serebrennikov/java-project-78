package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;;

public class ValidatorTest {
    @Test
    public void testRequiredWithNonNullString() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.required().isValid("TestString"));
    }
    @Test
    public void testRequiredWithNullString() {
        StringSchema schema = new StringSchema();
        assertFalse(schema.required().isValid(null));
    }
    @Test
    public void testMinLengthWithValidString() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.minLength(5).isValid("ValidString"));
    }
    @Test
    public void testMinLengthWithInvalidString() {
        StringSchema schema = new StringSchema();
        assertFalse(schema.minLength(10).isValid("Short"));
    }
    @Test
    public void testContainsWithContainedSubstring() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.contains("apple").isValid("I love apples"));
    }
    @Test
    public void testContainsWithNonContainedSubstring() {
        StringSchema schema = new StringSchema();
        assertFalse(schema.contains("fruits").isValid("I love apples"));
    }
    @Test
    public void testRequiredWithValidValue() {
        NumberSchema schema = new NumberSchema();
        Object validNumber = 77;
        assertTrue(schema.required().isValid(validNumber));
    }
    @Test
    public void testRequiredWithNullValue() {
        NumberSchema schema = new NumberSchema();
        assertFalse(schema.required().isValid(null));
    }
    @Test
    public void testRequiredWithNonNumberValue() {
        NumberSchema schema = new NumberSchema();
        Object nonNumberValue = "String";
        assertFalse(schema.required().isValid(nonNumberValue));
    }
    @Test
    public void testPositiveWithPositiveValue() {
        NumberSchema schema = new NumberSchema();
        Object positiveNumber = 10;

        assertTrue(schema.positive().isValid(positiveNumber));
    }
    @Test
    public void testPositiveWithNegativeValue() {
        NumberSchema schema = new NumberSchema();
        Object negativeNumber = -5;

        assertFalse(schema.positive().isValid(negativeNumber));
    }
    @Test
    public void testRangeWithInclusiveValue() {
        NumberSchema schema = new NumberSchema();
        Object valueInRange = 5;

        assertTrue(schema.range(5, 10).isValid(valueInRange));
    }
    @Test
    public void testRangeWithOutOfRangeValue() {
        NumberSchema schema = new NumberSchema();
        Object valueOutOfRange = 3;

        assertFalse(schema.range(5, 10).isValid(valueOutOfRange));
    }
    @Test
    public void testRequiredWithValidMap() {
        MapSchema schema = new MapSchema();
        Map<String, Integer> validMap = new HashMap<>();
        validMap.put("key1", 1);
        validMap.put("key2", 2);
        assertTrue(schema.required().isValid(validMap));
    }
    @Test
    public void testRequiredWithNullMap() {
        MapSchema schema = new MapSchema();
        assertFalse(schema.required().isValid(null));
    }

    @Test
    public void testSizeOfWithValidSize() {
        MapSchema schema = new MapSchema();
        Map<String, Integer> validMap = new HashMap<>();
        validMap.put("key1", 1);
        validMap.put("key2", 2);

        assertTrue(schema.sizeof(2).isValid(validMap));
    }

    @Test
    public void testSizeOfWithInvalidSize() {
        MapSchema schema = new MapSchema();
        Map<String, Integer> validMap = new HashMap<>();
        validMap.put("key1", 1);
        validMap.put("key2", 2);

        assertFalse(schema.sizeof(3).isValid(validMap));
    }
    @Test
    public void testShapeWithValidMap() {
        MapSchema schema = new MapSchema();
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
        MapSchema schema = new MapSchema();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("key1", new NumberSchema().positive());
        schemas.put("key2", new StringSchema().minLength(3));
        Map<String, Object> invalidMap = new HashMap<>();
        invalidMap.put("key1", -5);
        invalidMap.put("key2", "ab");

        assertFalse(schema.shape(schemas).isValid(invalidMap));
    }
}
