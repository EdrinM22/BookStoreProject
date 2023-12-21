package Staff;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

class WorkerDataTest {

    @TempDir
    File tempDir;

    private WorkerData workerData;

    @BeforeEach
    void setUp() {
        // Use a temporary file for testing
        File tempFile = new File(tempDir, "workers.dat");
        workerData = new WorkerData();
        workerData.file = tempFile;
        workerData.workerData = new ArrayList<>();
    }

    @Test
    void testReadWorkerData() {
        // Create a test worker
        Librarian testLibrarian = new Librarian("John Doe", "1234567890", "john@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        // Write the worker to the file
        assertTrue(workerData.writeWorkerToFile(testLibrarian));

        // Clear the workerData list to simulate reading from the file
        workerData.workerData.clear();

        // Read workers from the file
        assertDoesNotThrow(() -> workerData.readWorkerData());
        assertEquals(1, workerData.workerData.size());
        assertEquals(testLibrarian, workerData.workerData.get(0));
    }

    @Test
    void testDeleteWorker() {
        // Create a test worker
        Librarian testLibrarian = new Librarian("John Doe", "1234567890", "john@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        // Add the worker to the list
        workerData.workerData.add(testLibrarian);

        // Delete the worker
        workerData.deleteWorker(testLibrarian);

        // Verify that the worker has been removed
        assertEquals(0, workerData.workerData.size());
    }

    @Test
    void testRewriteFile() {
        // Create a test worker
        Librarian testLibrarian = new Librarian("John Doe", "1234567890", "john@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        // Add the worker to the list
        workerData.workerData.add(testLibrarian);

        // Rewrite the file
        assertTrue(workerData.rewirteFile());

        // Clear the workerData list to simulate reading from the file
        workerData.workerData.clear();

        // Read workers from the file
        assertDoesNotThrow(() -> workerData.readWorkerData());
        assertEquals(1, workerData.workerData.size());
    }

    @Test
    void testWriteWorkerToFile() {
        // Create a test worker
        Librarian testLibrarian = new Librarian("John Doe", "1234567890", "john@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        // Write the worker to the file
        assertTrue(workerData.writeWorkerToFile(testLibrarian));

        // Clear the workerData list to simulate reading from the file
        workerData.workerData.clear();

        // Read workers from the file
        assertDoesNotThrow(() -> workerData.readWorkerData());
        assertEquals(1, workerData.workerData.size());
        assertEquals(testLibrarian, workerData.workerData.get(0));
    }

    @Test
    void testWriteAllData() {
        // Create test workers
        Librarian testLibrarian = new Librarian("John Doe", "1234567890", "john@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        Manager testManager = new Manager("Jane Doe", "9876543210", "jane@example.com",
                2500.0f, "2000-02-01", Gender.FEMALE, "password", Worker.ACCESSLEVEL.MANAGER, true, false);

        Admin testAdmin = new Admin("Admin User", "5555555555", "admin@example.com",
                3000.0f, "2000-03-01", Gender.MALE, "admin_password", Worker.ACCESSLEVEL.ADMIN);

        // Add test workers to the list
        workerData.workerData.add(testLibrarian);
        workerData.workerData.add(testManager);
        workerData.workerData.add(testAdmin);

        // Write all data to the file
        assertTrue(workerData.writeAllData());

        // Clear the workerData list to simulate reading from the file
        workerData.workerData.clear();

        // Read workers from the file
        assertDoesNotThrow(() -> workerData.readWorkerData());
        assertEquals(3, workerData.workerData.size());
        assertTrue(workerData.workerData.contains(testLibrarian));
        assertTrue(workerData.workerData.contains(testManager));
        assertTrue(workerData.workerData.contains(testAdmin));
    }

    @Test
    void testNewWorkerForm() {
        // TODO: Add tests for the newWorkerForm method
    }

    @Test
    void testEditWorker() {
        // TODO: Add tests for the editWorker method
    }

    @Test
    void testGetData() {
        // Create test workers
        Librarian testLibrarian = new Librarian("John Doe", "1234567890", "john@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        Manager testManager = new Manager("Jane Doe", "9876543210", "jane@example.com",
                2500.0f, "2000-02-01", Gender.FEMALE, "password", Worker.ACCESSLEVEL.MANAGER, true, false);

        Admin testAdmin = new Admin("Admin User", "5555555555", "admin@example.com",
                3000.0f, "2000-03-01", Gender.MALE, "admin_password", Worker.ACCESSLEVEL.ADMIN);

        // Add test workers to the list
        workerData.workerData.add(testLibrarian);
        workerData.workerData.add(testManager);
        workerData.workerData.add(testAdmin);

        // Get the workerData list
        ArrayList<Worker> result = workerData.getData();

        // Verify that the result contains all test workers
        assertEquals(3, result.size());
        assertTrue(result.contains(testLibrarian));
        assertTrue(result.contains(testManager));
        assertTrue(result.contains(testAdmin));
    }

    @Test
    void testGetLibrarians() {
        // Create test librarians and a manager
        Librarian librarian1 = new Librarian("Librarian 1", "1234567890", "librarian1@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        Librarian librarian2 = new Librarian("Librarian 2", "9876543210", "librarian2@example.com",
                "2000-02-01", Gender.FEMALE, 2500.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, false);

        Manager manager = new Manager("Manager", "5555555555", "manager@example.com",
                3000.0f, "2000-03-01", Gender.MALE, "manager_password", Worker.ACCESSLEVEL.MANAGER, true, false);

        // Add test librarians and manager to the list
        workerData.workerData.add(librarian1);
        workerData.workerData.add(librarian2);
        workerData.workerData.add(manager);

        // Get the list of librarians
        ArrayList<Librarian> result = workerData.getLibrarians();

        // Verify that the result contains only librarians
        assertEquals(2, result.size());
        assertTrue(result.contains(librarian1));
        assertTrue(result.contains(librarian2));
        assertFalse(result.contains(manager));
    }

    @Test
    void testGetWorkerFromEmail() {
        // Create test workers
        Librarian librarian = new Librarian("Librarian", "1234567890", "librarian@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        Manager manager = new Manager("Manager", "9876543210", "manager@example.com",
                2500.0f, "2000-02-01", Gender.FEMALE, "password", Worker.ACCESSLEVEL.MANAGER, true, false);

        Admin admin = new Admin("Admin", "5555555555", "admin@example.com",
                3000.0f, "2000-03-01", Gender.MALE, "admin_password", Worker.ACCESSLEVEL.ADMIN);

        // Add test workers to the list
        workerData.workerData.add(librarian);
        workerData.workerData.add(manager);
        workerData.workerData.add(admin);

        // Get a worker using email
        Worker result = workerData.getWorkerFromEmail("manager@example.com");

        // Verify that the result is the manager
        assertEquals(manager, result);
    }

    @Test
    void testGetWorker() {
        // Create a test worker
        Librarian testLibrarian = new Librarian("John Doe", "1234567890", "john@example.com",
                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);

        // Add the worker to the list
        workerData.workerData.add(testLibrarian);

        // Get the worker at a specific index
        Worker result = workerData.getWorker(0);

        // Verify that the result is the testLibrarian
        assertEquals(testLibrarian, result);
    }

    @Test
    void testGetWorkerInvalidIndex() {
        // Attempt to get a worker with an invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> workerData.getWorker(5));
    }
}
