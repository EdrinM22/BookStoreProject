package Staff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {

    @Test
    void testConstructor() {
        Librarian librarian = new Librarian("Alice Smith", "123-456-7890", "alice@example.com", "1990-01-01", Gender.FEMALE, 50000.0f, "password123", Worker.ACCESSLEVEL.LIBRARIAN, true);

        assertEquals("Alice Smith", librarian.getFullName());
        assertEquals("123-456-7890", librarian.getPhone());
        assertEquals("alice@example.com", librarian.getEmail());
        assertEquals("1990-01-01", librarian.getDateOfBirth());
        assertEquals(Gender.FEMALE, librarian.getGender());
        assertEquals(50000.0f, librarian.getSalary());
        assertEquals("password123", librarian.getPassword());
        assertEquals(Worker.ACCESSLEVEL.LIBRARIAN, librarian.getACCESSLEVEL());
        assertTrue(librarian.isPermitionToBill());
    }

    @Test
    void testInteract() {
        Librarian librarian = new Librarian("Alice Smith", "123-456-7890", "alice@example.com", "1990-01-01", Gender.FEMALE, 50000.0f, "password123", Worker.ACCESSLEVEL.LIBRARIAN, true);
        assertDoesNotThrow(librarian::interact);
    }

    @Test
    void testPermitionToBill() {
        Librarian librarian = new Librarian("Alice Smith", "123-456-7890", "alice@example.com", "1990-01-01", Gender.FEMALE, 50000.0f, "password123", Worker.ACCESSLEVEL.LIBRARIAN, true);

        assertTrue(librarian.isPermitionToBill());

        librarian.setPermitionToBill(false);
        assertFalse(librarian.isPermitionToBill());
    }

    @Test
    void testSetPermitionToBill() {
        Librarian librarian = new Librarian("Alice Smith", "123-456-7890", "alice@example.com", "1990-01-01", Gender.FEMALE, 50000.0f, "password123", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.setPermitionToBill(false);
        assertFalse(librarian.isPermitionToBill());
    }
}
