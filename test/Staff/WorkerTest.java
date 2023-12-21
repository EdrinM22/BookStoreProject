package Staff;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WorkerTest {

    @Test
    void testInteract() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        assertDoesNotThrow(librarian::interact);
    }

    @Test
    void testAddPurchases() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.addPurchases(100.0);
        assertEquals(100.0, librarian.getTotalPurchases());
    }

    @Test
    void testAddBuys() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.addBuys(50.0);
        assertEquals(50.0, librarian.getTotalBuys());
    }

    @Test
    void testGetStatus() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        assertEquals("Librarian", librarian.getStatus());
    }

    @Test
    void testSetFullName() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.setFullName("Jane Doe");
        assertEquals("Jane Doe", librarian.getFullName());
    }

    @Test
    void testSetAccessLevel() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.setACCESSLEVEL(Worker.ACCESSLEVEL.ADMIN);
        assertEquals(Worker.ACCESSLEVEL.ADMIN, librarian.getACCESSLEVEL());
        librarian.setACCESSLEVEL(Worker.ACCESSLEVEL.MANAGER);
        assertEquals(Worker.ACCESSLEVEL.MANAGER, librarian.getACCESSLEVEL());
    }

    @Test
    void testSetPhone() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.setPhone("987-654-3210");
        assertEquals("987-654-3210", librarian.getPhone());
    }

    @Test
    void testSetSalary() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.setSalary(6000.0f);
        assertEquals(6000.0f, librarian.getSalary());
    }

    @Test
    void testSetEmail() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.setEmail("jane@example.com");
        assertEquals("jane@example.com", librarian.getEmail());
    }

    @Test
    void testSetDateOfBirth() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.setDateOfBirth("02-02-1995");
        assertEquals("02-02-1995", librarian.getDateOfBirth());
    }

    @Test
    void testSetPassword() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.setPassword("newpassword");
        assertEquals("newpassword", librarian.getPassword());
    }

    @Test
    void testGetTotalBuys() {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        librarian.addBuys(30.0);
        librarian.addBuys(20.0);
        assertEquals(50.0, librarian.getTotalBuys());
    }

}
