package Staff;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.util.ArrayList;

class WorkerDataTest {

//    @TempDir
//    File tempDir;
//
//    private WorkerData workerData;
//
//    @BeforeEach
//    void setUp() {
//        File tempFile = new File(tempDir, "Testworkers.dat");
//        workerData = new WorkerData();
//        workerData.file = tempFile;
//        workerData.workerData = new ArrayList<>();
//    }
//
//    @Test
//    void testReadWorkerData() {
//        Librarian testLibrarian = new Librarian("JohnDoe", "1234567890", "john@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        assertTrue(workerData.writeWorkerToFile(testLibrarian));
//
//        assertDoesNotThrow(() -> workerData.readWorkerData());
//        assertEquals(1, workerData.workerData.size());
//        assertEquals(testLibrarian, workerData.workerData.get(0));
//    }
//
//    @Test
//    void testDeleteWorker() {
//        Librarian testLibrarian = new Librarian("JohnDoe", "1234567890", "john@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        workerData.workerData.add(testLibrarian);
//        workerData.deleteWorker(testLibrarian);
//
//        assertEquals(0, workerData.workerData.size());
//    }
//
//    @Test
//    void testRewriteFile() {
//        Librarian testLibrarian = new Librarian("JohnDoe", "1234567890", "john@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        workerData.workerData.add(testLibrarian);
//
//        assertTrue(workerData.rewirteFile(tempDir));
//
//        assertDoesNotThrow(() -> workerData.readWorkerData());
//        assertEquals(1, workerData.workerData.size());
//    }
//
//    @Test
//    void testWriteWorkerToFile() {
//        Librarian testLibrarian = new Librarian("JohnDoe", "1234567890", "john@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        assertTrue(workerData.writeWorkerToFile(testLibrarian));
//
//        assertDoesNotThrow(() -> workerData.readWorkerData());
//        assertEquals(1, workerData.workerData.size());
//        assertEquals(testLibrarian, workerData.workerData.get(0));
//    }
//
//    @Test
//    void testWriteAllData() {
//        Librarian testLibrarian = new Librarian("JohnDoe", "1234567890", "john@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        Manager testManager = new Manager("JaneDoe", "9876543210", "jane@example.com",
//                2500.0f, "2000-02-01", Gender.FEMALE, "password", Worker.ACCESSLEVEL.MANAGER, true, false);
//
//        Admin testAdmin = new Admin("AdminUser", "5555555555", "admin@example.com",
//                3000.0f, "2000-03-01", Gender.MALE, "admin_password", Worker.ACCESSLEVEL.ADMIN);
//
//        workerData.workerData.add(testLibrarian);
//        workerData.workerData.add(testManager);
//        workerData.workerData.add(testAdmin);
//
//        assertTrue(workerData.writeAllData());
//
//        assertDoesNotThrow(() -> workerData.readWorkerData());
//        assertEquals(3, workerData.workerData.size());
//        assertTrue(workerData.workerData.contains(testLibrarian));
//        assertTrue(workerData.workerData.contains(testManager));
//        assertTrue(workerData.workerData.contains(testAdmin));
//    }
//
//    @Test
//    void testNewWorkerForm() {
//        // TODO: Add tests for the newWorkerForm method
//    }
//
//    @Test
//    void testEditWorker() {
//        // TODO: Add tests for the editWorker method
//    }
//
//    @Test
//    void testGetData() {
//        Librarian testLibrarian = new Librarian("JohnDoe", "1234567890", "john@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        Manager testManager = new Manager("JaneDoe", "9876543210", "jane@example.com",
//                2500.0f, "2000-02-01", Gender.FEMALE, "password", Worker.ACCESSLEVEL.MANAGER, true, false);
//
//        Admin testAdmin = new Admin("AdminUser", "5555555555", "admin@example.com",
//                3000.0f, "2000-03-01", Gender.MALE, "admin_password", Worker.ACCESSLEVEL.ADMIN);
//
//        workerData.workerData.add(testLibrarian);
//        workerData.workerData.add(testManager);
//        workerData.workerData.add(testAdmin);
//
//        ArrayList<Worker> result = workerData.getData();
//
//        assertEquals(3, result.size());
//        assertTrue(result.contains(testLibrarian));
//        assertTrue(result.contains(testManager));
//        assertTrue(result.contains(testAdmin));
//    }
//
//    @Test
//    void testGetLibrarians() {
//        Librarian librarian1 = new Librarian("Librarian1", "1234567890", "librarian1@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        Librarian librarian2 = new Librarian("Librarian2", "9876543210", "librarian2@example.com",
//                "2000-02-01", Gender.FEMALE, 2500.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, false);
//
//        Manager manager = new Manager("ManagerTest", "5555555555", "manager@example.com",
//                3000.0f, "2000-03-01", Gender.MALE, "manager_password", Worker.ACCESSLEVEL.MANAGER, true, false);
//
//        workerData.workerData.add(librarian1);
//        workerData.workerData.add(librarian2);
//        workerData.workerData.add(manager);
//
//        ArrayList<Librarian> result = workerData.getLibrarians();
//
//        assertEquals(2, result.size());
//        assertTrue(result.contains(librarian1));
//        assertTrue(result.contains(librarian2));
//        assertFalse(result.contains(manager));
//    }
//
//    @Test
//    void testGetWorkerFromEmail() {
//        Librarian librarian = new Librarian("LibrarianTest", "1234567890", "librarian@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        Manager manager = new Manager("ManagerTest", "9876543210", "manager@example.com",
//                2500.0f, "2000-02-01", Gender.FEMALE, "password", Worker.ACCESSLEVEL.MANAGER, true, false);
//
//        Admin admin = new Admin("AdminTest", "5555555555", "admin@example.com",
//                3000.0f, "2000-03-01", Gender.MALE, "admin_password", Worker.ACCESSLEVEL.ADMIN);
//
//        workerData.workerData.add(librarian);
//        workerData.workerData.add(manager);
//        workerData.workerData.add(admin);
//
//        Worker result = workerData.getWorkerFromEmail("manager@example.com");
//
//        assertEquals(manager, result);
//    }
//
//    @Test
//    void testGetWorker() {
//        Librarian testLibrarian = new Librarian("John Doe", "1234567890", "john@example.com",
//                "2000-01-01", Gender.MALE, 2000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
//
//        workerData.workerData.add(testLibrarian);
//
//        Worker result = workerData.getWorker(0);
//
//        assertEquals(testLibrarian, result);
//    }
//
//    @Test
//    void testGetWorkerInvalidIndex() {
//        assertThrows(IndexOutOfBoundsException.class, () -> workerData.getWorker(5));
//    }
//    Destroys the worker database after running this test and idk why
}
