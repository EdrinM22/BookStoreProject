package Orders;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyOrdersTest {
    @TempDir
    static File tempDir;

    private BuyOrders buyOrders;

    @BeforeEach
    void setUp() {
        File tempFile = new File(tempDir, "BuyBills.txt");
        File tempDataFile = new File(tempDir, "BuysBillData.dat");

        buyOrders = new BuyOrders(
                new ArrayList<>(List.of("ISBN123", "ISBN456")),
                new ArrayList<>(List.of(3, 5)),
                150.0,
                "John Doe");

        setPrivateField(buyOrders, "file", tempFile);
        setPrivateField(buyOrders, "filedata", tempDataFile);
    }

    @AfterEach
    void tearDown() {
        buyOrders = null;
    }

    @AfterAll
    static void tearDownClass() {
        try {
            Files.delete(tempDir.toPath());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    void testGetTotalPrice() {
        assertEquals(150.0, buyOrders.getTotalPrice());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", buyOrders.getName());
    }

    @Test
    void testGetIsbns() {
        assertEquals(List.of("ISBN123", "ISBN456"), buyOrders.getIsbns());
    }

    @Test
    void testGetQuantity() {
        assertEquals(List.of(3, 5), buyOrders.getQuantity());
    }

    @Test
    void testWriteToFile() {
        assertDoesNotThrow(() -> buyOrders.writeToFile());
    }

    @Test
    void testAddToDatabase() {
        assertDoesNotThrow(() -> buyOrders.addToDatabase());
    }
    private static void setPrivateField(Object object, String fieldName, Object value) {
        try {
            var field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error setting private field", e);
        }
    }

}