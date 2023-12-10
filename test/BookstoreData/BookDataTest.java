package BookstoreData;

import Orders.BuyOrders;
import Orders.PurchaseOrders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookDataTest {
    private BookData bookData;

    @BeforeEach
    void setUp() {
        bookData = new BookData();
    }

    @Test
    void testReadBookData() {
        assertNotNull(bookData.books);
    }

    @Test
    void testGetBooks() {
        ArrayList<Book> allBooks = bookData.getBooks();
        assertNotNull(allBooks);
        assertTrue(allBooks.size() > 0);
    }

    private BuyOrders createBuyOrder() {
        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add("ISBN1234567890");
        isbns.add("ISBN0987654321");
        quantities.add(5);
        quantities.add(10);
        return new BuyOrders(isbns, quantities, 0, "Supplier");
    }

    private PurchaseOrders createSellOrder() {
        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add("ISBN1234567890");
        isbns.add("ISBN0987654321");
        quantities.add(5);
        quantities.add(5);
        double totalPrice = 0.0;
        return new PurchaseOrders(isbns, quantities, totalPrice, "Customer");
    }

}