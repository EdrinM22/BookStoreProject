package Staff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GenderTest {
    @Test
    void testEnumConstants() {
        assertEquals(2, Gender.values().length);
        assertEquals(Gender.MALE, Gender.valueOf("MALE"));
        assertEquals(Gender.FEMALE, Gender.valueOf("FEMALE"));
    }

    @Test
    void testToString() {
        assertEquals("MALE", Gender.MALE.toString());
        assertEquals("FEMALE", Gender.FEMALE.toString());
    }

    @Test
    void testEquality() {
        assertNotEquals(Gender.MALE, Gender.FEMALE);
        assertEquals(Gender.MALE, Gender.MALE);
        assertEquals(Gender.FEMALE, Gender.FEMALE);
    }

    @Test
    void testHashCode() {
        assertEquals(Gender.MALE.hashCode(), Gender.MALE.hashCode());
        assertEquals(Gender.FEMALE.hashCode(), Gender.FEMALE.hashCode());
        assertNotEquals(Gender.MALE.hashCode(), Gender.FEMALE.hashCode());
    }
}
