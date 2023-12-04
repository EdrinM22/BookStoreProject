package Staff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkerTest {
    private static class TestWorker extends Worker {
        public TestWorker(String fullName, String phone, String email, String dateOfBirth,
                          float salary, String password, ACCESSLEVEL accessLevel, Gender gender) {
            super(fullName, phone, email, dateOfBirth, salary, password, accessLevel, gender);
        }

        @Override
        public void interact() {
        }
    }
    @Test
    void testConstructor() {
        TestWorker worker = new TestWorker("John Doe", "123-456-7890", "john.doe@example.com", "1990-01-01", 50000.0f, "password123", Worker.ACCESSLEVEL.MANAGER, Gender.MALE);

        assertEquals("John Doe", worker.getFullName());
        assertEquals("123-456-7890", worker.getPhone());
        assertEquals("john.doe@example.com", worker.getEmail());
        assertEquals("1990-01-01", worker.getDateOfBirth());
        assertEquals(50000.0f, worker.getSalary());
        assertEquals("password123", worker.getPassword());
        assertEquals(Worker.ACCESSLEVEL.MANAGER, worker.getACCESSLEVEL());
        assertEquals(Gender.MALE, worker.getGender());
        assertEquals("Manager", worker.getStatus());
    }

    @Test
    void testInteract() {
        TestWorker worker = new TestWorker("John Doe", "123-456-7890", "john.doe@example.com", "1990-01-01", 50000.0f, "password123", Worker.ACCESSLEVEL.MANAGER, Gender.MALE);
        assertDoesNotThrow(worker::interact);
    }

    @Test
    void testAddPurchases() {
        TestWorker worker = new TestWorker("John Doe", "123-456-7890", "john.doe@example.com", "1990-01-01", 50000.0f, "password123", Worker.ACCESSLEVEL.MANAGER, Gender.MALE);

        worker.addPurchases(100.0);
        assertEquals(100.0, worker.getTotalPurchases());
    }

    @Test
    void testAddBuys() {
        TestWorker worker = new TestWorker("John Doe", "123-456-7890", "john.doe@example.com", "1990-01-01", 50000.0f, "password123", Worker.ACCESSLEVEL.MANAGER, Gender.MALE);

        worker.addBuys(50.0);
        assertEquals(50.0, worker.getTotalBuys());
    }

    @Test
    void testSetStatus() {
        TestWorker worker = new TestWorker("John Doe", "123-456-7890", "john.doe@example.com", "1990-01-01", 50000.0f, "password123", Worker.ACCESSLEVEL.MANAGER, Gender.MALE);

        worker.setStatus(Worker.ACCESSLEVEL.LIBRARIAN);
        assertEquals("Librarian", worker.getStatus());
    }
}
