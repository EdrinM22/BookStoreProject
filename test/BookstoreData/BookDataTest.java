package BookstoreData;

import Orders.BuyOrders;
import Orders.PurchaseOrders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BookDataTest {

    private BookData bookData;
    @TempDir
    File tempDir;
    @BeforeEach
    void setUp() {
        // Create a temporary file for testing
        File tempFile = new File(tempDir,"TestBookDataFile.dat");
        bookData = new BookData();
        bookData.file = tempFile;
    }
    @Test
    void testReadBookData() {

        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        bookData.books.addAll(Arrays.asList(book1, book2));
        bookData.rewirteFile();


        bookData.books.clear();

        assertDoesNotThrow(bookData::readBookData);
        assertEquals(13, bookData.books.size());
    }

    @Test
    void testCheckIsbn13Valid() {
        assertTrue(bookData.checkIsbn13("1234567890123"));
    }

    @Test
    void testCheckIsbn13Invalid() {
        assertFalse(bookData.checkIsbn13("InvalidISBN"));
    }

    @Test
    void testWriteBookToFile() {

        Book testBook = new Book("1111111111111", "Test Book", "Test Description", 24.99f, "Test Author", true, 5);

        assertTrue(bookData.writeBookToFile(testBook));

        bookData.books.clear();

        assertDoesNotThrow(bookData::readBookData);
        assertEquals(1, bookData.books.size());
    }

    @Test
    void testRewriteFile() {

        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        bookData.books.addAll(Arrays.asList(book1, book2));

        assertTrue(bookData.rewirteFile());

        bookData.books.clear();

        assertDoesNotThrow(bookData::readBookData);
        assertEquals(13, bookData.books.size());
    }

    @Test
    void testAddBooksToStock() {

        BuyOrders buyOrder = new BuyOrders(
                new ArrayList<>(Arrays.asList("1234567890123", "9876543210123")),
                new ArrayList<>(Arrays.asList(5, 10)),
                0.0, "TestSupplier"
        );

        bookData.addBooksToStock(buyOrder);
        assertEquals(0, bookData.getBookQuantity("1234567890123"));
        assertEquals(0, bookData.getBookQuantity("9876543210123"));
    }

    @Test
    void testRemoveBooksFromStock() {

        PurchaseOrders sellOrder = new PurchaseOrders(
                new ArrayList<>(Arrays.asList("1234567890123", "9876543210123")),
                new ArrayList<>(Arrays.asList(2, 5)),
                0.0, "TestCustomer"
        );

        bookData.removeBooksFromStock(sellOrder);

        assertEquals(0, bookData.getBookQuantity("1234567890123"));
        assertEquals(0, bookData.getBookQuantity("9876543210123"));
    }

    @Test
    void testGetBook() {

        Book testBook = new Book("1234567890123", "Test Book", "Test Description", 24.99f, "Test Author", true, 5);

        bookData.books.add(testBook);

        Book retrievedBook = bookData.getBook("1234567890123");

        assertEquals(testBook, retrievedBook);
    }

    @Test
    void testGetFromName() {

        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        bookData.books.addAll(Arrays.asList(book1, book2));

        ArrayList<Book> result = bookData.getFromName("Test");

        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
    }

    @Test
    void testSearchByTitle() {

        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        bookData.books.addAll(Arrays.asList(book1, book2));

        Book result = bookData.searchByTitle("Test Book 1");

        assertEquals(book1, result);
    }

    @Test
    void testGetBooks() {

        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        bookData.books.addAll(Arrays.asList(book1, book2));

        ArrayList<Book> result = bookData.getBooks();

        assertEquals(13, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
    }

    @Test
    void testGetBookQuantity() {

        Book testBook = new Book("1234567890123", "Test Book", "Test Description", 24.99f, "Test Author", true, 5);

        bookData.books.add(testBook);

        int quantity = bookData.getBookQuantity("1234567890123");

        assertEquals(5, quantity);
    }

    @Test
    void testCheckIsbn13ValidStatic() {
        assertTrue(BookData.checkIsbn13("1234567890123"));
    }

    @Test
    void testCheckIsbn13InvalidStatic() {

        assertFalse(BookData.checkIsbn13("InvalidISBN"));
    }
    @Test
    void testAddBooksToStockBookNotFound() {

        BuyOrders buyOrder = new BuyOrders(
                new ArrayList<>(Collections.singletonList("NonExistingISBN")),
                new ArrayList<>(Collections.singletonList(5)),
                0.0, "TestSupplier"
        );

        assertDoesNotThrow(() -> bookData.addBooksToStock(buyOrder));
    }

    @Test
    void testRemoveBooksFromStockBookNotFound() {

        PurchaseOrders sellOrder = new PurchaseOrders(
                new ArrayList<>(Collections.singletonList("NonExistingISBN")),
                new ArrayList<>(Collections.singletonList(2)),
                0.0, "TestCustomer"
        );

        assertDoesNotThrow(() -> bookData.removeBooksFromStock(sellOrder));
    }

    @Test
    void testGetBookQuantityBookNotFound() {

        int quantity = bookData.getBookQuantity("NonExistingISBN");
        assertEquals(0, quantity);
    }

    @Test
    void testGetBookQuantityValid() {
        Book testBook = new Book("1234567890123", "Test Book", "Test Description", 24.99f, "Test Author", true, 5);
        bookData.books.add(testBook);

        int quantity = bookData.getBookQuantity("1234567890123");

        assertEquals(5, quantity);
    }

    @Test
    void testCheckIsbn13ValidStaticInvalid() {
        assertFalse(BookData.checkIsbn13("1234567890ABC"));
    }

    @Test
    void testWriteBookToFileIOException() {
        Book testBook = new Book("1111111111111", "Test Book", "Test Description", 24.99f, "Test Author", true, 5);

        bookData.file = new File("/readonly/path/TestBookDataFile.dat");

        assertFalse(bookData.writeBookToFile(testBook));
    }

    @Test
    void testRewriteFileIOException() {
        Book testBook = new Book("1234567890123", "Test Book", "Description", 29.99f, "Author", false, 10);

        bookData.file = new File("/readonly/path/TestBookDataFile.dat");

        assertFalse(bookData.rewirteFile());
    }
    @Test
    void testAddBooksToStockMultipleBooks() {
        BuyOrders buyOrder = new BuyOrders(
                new ArrayList<>(Arrays.asList("1234567890123", "9876543210123")),
                new ArrayList<>(Arrays.asList(5, 10)),
                0.0, "TestSupplier"
        );

        bookData.addBooksToStock(buyOrder);

        assertEquals(0, bookData.getBookQuantity("1234567890123"));
        assertEquals(0, bookData.getBookQuantity("9876543210123"));

        BuyOrders additionalBuyOrder = new BuyOrders(
                new ArrayList<>(Arrays.asList("1234567890123", "9876543210123")),
                new ArrayList<>(Arrays.asList(3, 8)),
                0.0, "TestSupplier"
        );

        bookData.addBooksToStock(additionalBuyOrder);

        assertEquals(0, bookData.getBookQuantity("1234567890123"));
        assertEquals(0, bookData.getBookQuantity("9876543210123"));
    }

    @Test
    void testRemoveBooksFromStockMultipleBooks() {
        PurchaseOrders sellOrder = new PurchaseOrders(
                new ArrayList<>(Arrays.asList("1234567890123", "9876543210123")),
                new ArrayList<>(Arrays.asList(2, 5)),
                0.0, "TestCustomer"
        );

        bookData.removeBooksFromStock(sellOrder);

        assertEquals(0, bookData.getBookQuantity("1234567890123"));
        assertEquals(0, bookData.getBookQuantity("9876543210123"));


        PurchaseOrders additionalSellOrder = new PurchaseOrders(
                new ArrayList<>(Arrays.asList("1234567890123", "9876543210123")),
                new ArrayList<>(Arrays.asList(1, 3)),
                0.0, "TestCustomer"
        );

        bookData.removeBooksFromStock(additionalSellOrder);

        assertEquals(0, bookData.getBookQuantity("1234567890123"));
        assertEquals(0, bookData.getBookQuantity("9876543210123"));
    }

    @Test
    void testSearchByTitleNotFound() {
        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        bookData.books.addAll(Arrays.asList(book1, book2));

        Book result = bookData.searchByTitle("Nonexistent Book");

        assertNull(result);
    }

    @Test
    void testGetBooksEmptyList() {
        bookData.books.clear();

        ArrayList<Book> result = bookData.getBooks();

        assertEquals(0, result.size());
    }

    @Test
    void testGetBooksByNameEmptyList() {
        bookData.books.clear();

        ArrayList<Book> result = bookData.getFromName("Test");

        assertEquals(0, result.size());
    }

    @Test
    void testWriteBookToFileNullBook() {
        assertTrue(bookData.writeBookToFile(null));
        assertEquals(11, bookData.books.size());
    }

    @Test
    void testRewriteFileEmptyBooksList() {
        bookData.books.clear();
        assertTrue(bookData.rewirteFile());
        bookData.books.clear();
        assertDoesNotThrow(bookData::readBookData);
        assertEquals(0, bookData.books.size());
    }

    @Test
    void testAddBooksToStockEmptyOrder() {
        BuyOrders emptyBuyOrder = new BuyOrders(
                new ArrayList<>(),
                new ArrayList<>(),
                0.0, "TestSupplier"
        );
        bookData.addBooksToStock(emptyBuyOrder);
        assertEquals(11, bookData.books.size());
    }

    @Test
    void testRemoveBooksFromStockEmptyOrder() {
        PurchaseOrders emptySellOrder = new PurchaseOrders(
                new ArrayList<>(),
                new ArrayList<>(),
                0.0, "TestCustomer"
        );
        bookData.removeBooksFromStock(emptySellOrder);
        assertEquals(11, bookData.books.size());
    }

    @Test
    void testGetBookNotFound() {
        Book result = bookData.getBook("NonexistentISBN");
        assertNull(result);
    }

    @Test
    void testSearchByTitleEmptyTitle() {
        Book result = bookData.searchByTitle("");
        assertNull(result);
    }

    @Test
    void testGetFromNameEmptyName() {
        ArrayList<Book> result = bookData.getFromName("");
        assertEquals(11, result.size());
    }

    @Test
    void testSearchByTitleNullTitle() {
        Book result = bookData.searchByTitle(null);
        assertNull(result);
    }

    @Test
    void testGetBookQuantityNullISBN() {
        int quantity = bookData.getBookQuantity(null);
        assertEquals(0, quantity);
    }


    @Test
    void testAddBooksToStockInvalidQuantity() {
        BuyOrders invalidBuyOrder = new BuyOrders(
                new ArrayList<>(Collections.singletonList("1234567890123")),
                new ArrayList<>(Collections.singletonList(-5)),
                0.0, "TestSupplier"
        );
        assertDoesNotThrow(() -> bookData.addBooksToStock(invalidBuyOrder));
    }

    @Test
    void testRemoveBooksFromStockInvalidQuantity() {
        PurchaseOrders invalidSellOrder = new PurchaseOrders(
                new ArrayList<>(Collections.singletonList("1234567890123")),
                new ArrayList<>(Collections.singletonList(-2)),
                0.0, "TestCustomer"
        );
        assertDoesNotThrow(() -> bookData.removeBooksFromStock(invalidSellOrder));
    }


}
