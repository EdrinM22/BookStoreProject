package Orders;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseOrdersTest {
    @Test
    void testGetTotalPrice() {
        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add("ISBN1234567890");
        isbns.add("ISBN0987654321");
        quantities.add(5);
        quantities.add(10);
        PurchaseOrders purchaseOrder = new PurchaseOrders(isbns, quantities, 150.0, "Supplier");

        assertEquals(150.0, purchaseOrder.getTotalPrice());
    }

    @Test
    void testGetName() {
        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add("ISBN1234567890");
        isbns.add("ISBN0987654321");
        quantities.add(5);
        quantities.add(10);
        PurchaseOrders purchaseOrder = new PurchaseOrders(isbns, quantities, 150.0, "Supplier");

        assertEquals("Supplier", purchaseOrder.getName());
    }

    @Test
    void testGetIsbns() {
        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add("ISBN1234567890");
        isbns.add("ISBN0987654321");
        quantities.add(5);
        quantities.add(10);
        PurchaseOrders purchaseOrder = new PurchaseOrders(isbns, quantities, 150.0, "Supplier");

        assertEquals(isbns, purchaseOrder.getIsbns());
    }

    @Test
    void testGetQuantity() {
        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add("ISBN1234567890");
        isbns.add("ISBN0987654321");
        quantities.add(5);
        quantities.add(10);
        PurchaseOrders purchaseOrder = new PurchaseOrders(isbns, quantities, 150.0, "Supplier");

        assertEquals(quantities, purchaseOrder.getQuantity());
    }

    @Test
    void testWriteToFile() {
        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add("ISBN1234567890");
        isbns.add("ISBN0987654321");
        quantities.add(5);
        quantities.add(10);
        PurchaseOrders purchaseOrder = new PurchaseOrders(isbns, quantities, 150.0, "Supplier");

        File file = new File("testPurchaseBills.txt");
        File purchaseFile = new File("testPurchaseBillData.dat");
        purchaseOrder.file = file;
        purchaseOrder.purchasefile = purchaseFile;

        assertDoesNotThrow(() -> purchaseOrder.writeToFile());

        // Verify the content of the written file
        try (Scanner scanner = new Scanner(file)) {
            assertEquals("PurchaseBill", scanner.nextLine().trim());
            assertTrue(scanner.nextLine().contains("Supplier"));
            assertTrue(scanner.nextLine().contains("ISBN-> ISBN1234567890"));
            assertTrue(scanner.nextLine().contains("Quantity 5"));
            assertTrue(scanner.nextLine().contains("ISBN-> ISBN0987654321"));
            assertTrue(scanner.nextLine().contains("Quantity 10"));
            assertTrue(scanner.nextLine().contains("Total Books: 15"));
            assertTrue(scanner.nextLine().contains("Total Price: 150.0"));
        } catch (FileNotFoundException e) {
            fail("File not found: " + file.getPath());
        }
    }

}