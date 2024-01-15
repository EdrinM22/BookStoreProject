package Staff;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkerDataTest extends ApplicationTest {

    private WorkerData workerData;
    private File testFile;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp(TestInfo testInfo) {

        String testName = testInfo.getTestMethod().get().getName();
        testFile = tempDir.resolve(testName + "_testWorkers.dat").toFile();
        workerData = new WorkerData();
        workerData.file = testFile;
        workerData.workerData = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {

        workerData.workerData.clear();
    }

    @Test
    void testReadWorkerData() {

        Worker testWorker = new Librarian("Test Librarian", "123456789", "test@example.com", "01-01-2000", Gender.MALE, 2000.0F, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        workerData.writeWorkerToFile(testWorker);


        workerData.readWorkerData();

        assertEquals(1, workerData.workerData.size());
        assertEquals("Test Librarian", workerData.workerData.get(0).getFullName());
    }

    @Test
    void testDeleteWorker() {

        Worker testWorker = new Librarian("Test Librarian", "123456789", "test@example.com", "01-01-2000", Gender.MALE, 2000.0F, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        workerData.workerData.add(testWorker);

        workerData.deleteWorker(testWorker);

        assertEquals(0, workerData.workerData.size());
    }

    @Test
    void testWriteWorkerToFile() {

        Worker testWorker = new Librarian("Test Librarian", "123456789", "test@example.com", "01-01-2000", Gender.MALE, 2000.0F, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        workerData.workerData.add(testWorker);


        workerData.rewirteFile(testFile);

        workerData.readWorkerData();

        assertEquals(2, workerData.workerData.size());
        assertEquals("Test Librarian", workerData.workerData.get(0).getFullName());
    }
    @Test
    void testDeleteNonExistingWorker() {
        Worker nonExistingWorker = new Librarian("Non Existing", "123456789", "nonexisting@example.com", "01-01-2000", Gender.MALE, 2000.0F, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        workerData.deleteWorker(nonExistingWorker);
        assertEquals(0, workerData.workerData.size());
    }
    @Test
    void testRewriteFile() {

        Worker testWorker = new Librarian("Test Librarian", "123456789", "test@example.com", "01-01-2000", Gender.MALE, 2000.0F, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        workerData.workerData.add(testWorker);


        assertTrue(workerData.rewirteFile(testFile));

        workerData.readWorkerData();

        assertEquals(2, workerData.workerData.size());
        assertEquals("Test Librarian", workerData.workerData.get(0).getFullName());
    }

    @Test
    void testWriteAllData() {

        Worker testWorker1 = new Librarian("Librarian 1", "123456789", "librarian1@example.com", "01-01-2000", Gender.MALE, 2000.0F, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        Worker testWorker2 = new Manager("Manager 1", "987654321", "manager1@example.com", 2500.0F, "01-02-2000", Gender.FEMALE, "password", Worker.ACCESSLEVEL.MANAGER, true, false);
        workerData.workerData.add(testWorker1);
        workerData.workerData.add(testWorker2);

        assertTrue(workerData.writeAllData(testFile));

        workerData.readWorkerData();

        assertEquals(4, workerData.workerData.size());
        assertEquals("Librarian 1", workerData.workerData.get(0).getFullName());
        assertEquals("Manager 1", workerData.workerData.get(1).getFullName());
    }

}
