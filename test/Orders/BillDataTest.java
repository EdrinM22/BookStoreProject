package Orders;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BillDataTest {
    @Test
    void testReadPurchaseBillsData() {
        BillData billData = new BillData();
        assertDoesNotThrow(billData::readPurchaseBillsData);
    }

    @Test
    void testReadBuyBillsData() {
        BillData billData = new BillData();
        assertDoesNotThrow(billData::readBuyBillsData);
    }

    @Test
    void testGetPurchasedBillsFromName() {
        BillData billData = new BillData();
        ArrayList<PurchaseOrders> purchasedBills = billData.getPurchasedBillsFromName("John Doe");
        assertNotNull(purchasedBills);
        assertEquals(0, purchasedBills.size());
    }

    @Test
    void testGetTotalSalesFromName() {
        BillData billData = new BillData();
        double totalSales = billData.getTotalSalesFromName("John Doe");
        assertEquals(0.0, totalSales);
    }

    @Test
    void testGetBuyBillsFromName() {
        BillData billData = new BillData();
        ArrayList<BuyOrders> buyBills = billData.getBuyBillsFromName("John Doe");
        assertNotNull(buyBills);
        assertEquals(0, buyBills.size());
    }

    @Test
    void testGetTotalBuysFromName() {
        BillData billData = new BillData();
        double totalBuys = billData.getTotalBuysFromName("John Doe");
        assertEquals(0.0, totalBuys);
    }
    @Test
    void testGetTotalSalesFromNonexistentName() {
        BillData billData = new BillData();
        double totalSales = billData.getTotalSalesFromName("NonexistentName");
        assertEquals(0.0, totalSales);
    }

    @Test
    void testGetTotalBuysFromNonexistentName() {
        BillData billData = new BillData();
        double totalBuys = billData.getTotalBuysFromName("NonexistentName");
        assertEquals(0.0, totalBuys);
    }
}
