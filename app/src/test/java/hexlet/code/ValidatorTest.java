package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}
