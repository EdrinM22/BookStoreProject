package Orders;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

class BillDataTest {

    @Test
    void testReadPurchaseBillsData() throws IOException {
        File tempFile = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream writer = new ObjectOutputStream(Files.newOutputStream(tempFile.toPath()))) {
            PurchaseOrders purchaseOrder = new PurchaseOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "TestName");
            writer.writeObject(purchaseOrder);
        }

        BillData billData = new BillData();
        billData.purchasefile = tempFile;

        assertDoesNotThrow(billData::readPurchaseBillsData);
        assertEquals(1, billData.purchases.size());
    }

    @Test
    void testReadBuyBillsData() throws IOException {
        File tempFile = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream writer = new ObjectOutputStream(Files.newOutputStream(tempFile.toPath()))) {
            BuyOrders buyOrder = new BuyOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "TestName");
            writer.writeObject(buyOrder);
        }

        BillData billData = new BillData();
        billData.buyfile = tempFile;

        assertDoesNotThrow(billData::readBuyBillsData);
        assertEquals(1, billData.buys.size());
    }

    @Test
    void testGetPurchasedBillsFromName() {
        BillData billData = new BillData();
        ArrayList<PurchaseOrders> purchases = new ArrayList<>();
        purchases.add(new PurchaseOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "TestName"));
        purchases.add(new PurchaseOrders(new ArrayList<>(), new ArrayList<>(), 150.0, "OtherName"));
        billData.purchases = purchases;

        ArrayList<PurchaseOrders> result = billData.getPurchasedBillsFromName("TestName");
        assertEquals(1, result.size());
        assertEquals(100.0, result.get(0).getTotalPrice());
    }

    @Test
    void testGetTotalSalesFromName() {
        BillData billData = new BillData();
        ArrayList<PurchaseOrders> purchases = new ArrayList<>();
        purchases.add(new PurchaseOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "TestName"));
        purchases.add(new PurchaseOrders(new ArrayList<>(), new ArrayList<>(), 150.0, "TestName"));
        billData.purchases = purchases;

        double result = billData.getTotalSalesFromName("TestName");
        assertEquals(250.0, result);
    }

    @Test
    void testGetBuyBillsFromName() {
        BillData billData = new BillData();
        ArrayList<BuyOrders> buys = new ArrayList<>();
        buys.add(new BuyOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "TestName"));
        buys.add(new BuyOrders(new ArrayList<>(), new ArrayList<>(), 150.0, "OtherName"));
        billData.buys = buys;

        ArrayList<BuyOrders> result = billData.getBuyBillsFromName("TestName");
        assertEquals(1, result.size());
        assertEquals(100.0, result.get(0).getTotalPrice());
    }

    @Test
    void testGetTotalBuysFromName() {
        BillData billData = new BillData();
        ArrayList<BuyOrders> buys = new ArrayList<>();
        buys.add(new BuyOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "TestName"));
        buys.add(new BuyOrders(new ArrayList<>(), new ArrayList<>(), 150.0, "TestName"));
        billData.buys = buys;

        double result = billData.getTotalBuysFromName("TestName");
        assertEquals(250.0, result);
    }

    @Test
    void testConstructor() {
        BillData billData = new BillData();

        assertNotNull(billData.purchases);
        assertNotNull(billData.buys);
        assertEquals(0, billData.purchases.size());
        assertEquals(0, billData.buys.size());
    }

    @Test
    void testGetPurchasedBillsFromNameNoMatches() {
        BillData billData = new BillData();
        ArrayList<PurchaseOrders> purchases = new ArrayList<>();
        purchases.add(new PurchaseOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "OtherName"));
        billData.purchases = purchases;

        ArrayList<PurchaseOrders> result = billData.getPurchasedBillsFromName("TestName");
        assertEquals(0, result.size());
    }

    @Test
    void testGetTotalSalesFromNameNoMatches() {
        BillData billData = new BillData();
        ArrayList<PurchaseOrders> purchases = new ArrayList<>();
        purchases.add(new PurchaseOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "OtherName"));
        billData.purchases = purchases;

        double result = billData.getTotalSalesFromName("TestName");
        assertEquals(0.0, result);
    }

    @Test
    void testGetBuyBillsFromNameNoMatches() {
        BillData billData = new BillData();
        ArrayList<BuyOrders> buys = new ArrayList<>();
        buys.add(new BuyOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "OtherName"));
        billData.buys = buys;

        ArrayList<BuyOrders> result = billData.getBuyBillsFromName("TestName");
        assertEquals(0, result.size());
    }

    @Test
    void testGetTotalBuysFromNameNoMatches() {
        BillData billData = new BillData();
        ArrayList<BuyOrders> buys = new ArrayList<>();
        buys.add(new BuyOrders(new ArrayList<>(), new ArrayList<>(), 100.0, "OtherName"));
        billData.buys = buys;

        double result = billData.getTotalBuysFromName("TestName");
        assertEquals(0.0, result);
    }
}
