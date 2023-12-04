package Staff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ManagerTest {
    @Test
    void testConstructor() {
        Manager manager = new Manager("John Doe", "123-456-7890", "john.doe@example.com", 50000.0f, "1990-01-01", Gender.MALE, "password123", Worker.ACCESSLEVEL.MANAGER, true, false);

        assertEquals("John Doe", manager.getFullName());
        assertEquals("123-456-7890", manager.getPhone());
        assertEquals("john.doe@example.com", manager.getEmail());
        assertEquals(50000.0f, manager.getSalary());
        assertEquals("1990-01-01", manager.getDateOfBirth());
        assertEquals(Gender.MALE, manager.getGender());
        assertEquals("password123", manager.getPassword());
        assertEquals(Worker.ACCESSLEVEL.MANAGER, manager.getACCESSLEVEL());
        assertTrue(manager.isPermitionToPurchse());
        assertFalse(manager.isCheckLibrarians());
    }

    @Test
    void testInteract() {
        Manager manager = new Manager("John Doe", "123-456-7890", "john.doe@example.com", 50000.0f, "1990-01-01", Gender.MALE, "password123", Worker.ACCESSLEVEL.MANAGER, true, false);
        assertDoesNotThrow(manager::interact);
    }
    @Test
    void testSetCheckLibrarians() {
        Manager manager = new Manager("John Doe", "123-456-7890", "john.doe@example.com", 50000.0f, "1990-01-01", Gender.MALE, "password123", Worker.ACCESSLEVEL.MANAGER, true, false);

        manager.setCheckLibrarians(true);
        assertTrue(manager.isCheckLibrarians());
    }
    @Test
    void testCheckLibrarians() {
        Manager manager = new Manager("John Doe", "123-456-7890", "john.doe@example.com", 50000.0f, "1990-01-01", Gender.MALE, "password123", Worker.ACCESSLEVEL.MANAGER, true, false);

        assertFalse(manager.isCheckLibrarians());

        manager.setCheckLibrarians(true);
        assertTrue(manager.isCheckLibrarians());

    }
    @Test
    void testSetPermitionToPurchse() {
        Manager manager = new Manager("John Doe", "123-456-7890", "john.doe@example.com", 50000.0f, "1990-01-01", Gender.MALE, "password123", Worker.ACCESSLEVEL.MANAGER, true, false);

        manager.setPermitionToPurchse(false);
        assertFalse(manager.isPermitionToPurchse());
    }
    @Test
    void testPermitionToPurchse() {
        Manager manager = new Manager("John Doe", "123-456-7890", "john.doe@example.com", 50000.0f, "1990-01-01", Gender.MALE, "password123", Worker.ACCESSLEVEL.MANAGER, true, false);

        assertTrue(manager.isPermitionToPurchse());

        manager.setPermitionToPurchse(false);
        assertFalse(manager.isPermitionToPurchse());
    }


}
