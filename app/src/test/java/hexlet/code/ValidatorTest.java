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
}
