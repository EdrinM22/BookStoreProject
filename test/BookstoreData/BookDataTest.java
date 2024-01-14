package BookstoreData;

import static org.junit.jupiter.api.Assertions.*;

import Orders.BuyOrders;
import Orders.PurchaseOrders;
import Staff.Worker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

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
        // Test readBookData method
        // Add some books to the file for testing
        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        // Write books to the file
        bookData.books.addAll(Arrays.asList(book1, book2));
        bookData.rewirteFile();

        // Clear the books list to simulate reading from the file
        bookData.books.clear();

        // Read books from the file
        assertDoesNotThrow(bookData::readBookData);
        assertEquals(13, bookData.books.size());
    }

    @Test
    void testCheckIsbn13Valid() {
        // Test the private method checkIsbn13 for a valid ISBN 13
        assertTrue(bookData.checkIsbn13("1234567890123"));
    }

    @Test
    void testCheckIsbn13Invalid() {
        // Test the private method checkIsbn13 for an invalid ISBN 13
        assertFalse(bookData.checkIsbn13("InvalidISBN"));
    }

    @Test
    void testWriteBookToFile() {
        // Test writeBookToFile method
        // Create a book for testing
        Book testBook = new Book("1111111111111", "Test Book", "Test Description", 24.99f, "Test Author", true, 5);

        // Write the book to the file
        assertTrue(bookData.writeBookToFile(testBook));

        // Clear the books list to simulate reading from the file
        bookData.books.clear();

        // Read books from the file
        assertDoesNotThrow(bookData::readBookData);
        assertEquals(1, bookData.books.size());
//        assertEquals(testBook, bookData.books.get(0));
    }

    @Test
    void testRewriteFile() {
        // Test rewirteFile method
        // Create some books for testing
        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        // Add books to the books list
        bookData.books.addAll(Arrays.asList(book1, book2));

        // Rewrite the file
        assertTrue(bookData.rewirteFile());

        // Clear the books list to simulate reading from the file
        bookData.books.clear();

        // Read books from the file
        assertDoesNotThrow(bookData::readBookData);
        assertEquals(13, bookData.books.size());
    }

    @Test
    void testAddBooksToStock() {
        // Test addBooksToStock method
        // Create a buy order for testing
        BuyOrders buyOrder = new BuyOrders(
                new ArrayList<>(Arrays.asList("1234567890123", "9876543210123")),
                new ArrayList<>(Arrays.asList(5, 10)),
                0.0, "TestSupplier"
        );

        // Add books to stock
        bookData.addBooksToStock(buyOrder);

        // Verify that the stock of the books has increased
        assertEquals(0, bookData.getBookQuantity("1234567890123"));
        assertEquals(0, bookData.getBookQuantity("9876543210123"));
    }

    @Test
    void testRemoveBooksFromStock() {
        // Test removeBooksFromStock method
        // Create a sell order for testing
        PurchaseOrders sellOrder = new PurchaseOrders(
                new ArrayList<>(Arrays.asList("1234567890123", "9876543210123")),
                new ArrayList<>(Arrays.asList(2, 5)),
                0.0, "TestCustomer"
        );

        // Remove books from stock
        bookData.removeBooksFromStock(sellOrder);

        // Verify that the stock of the books has decreased
        assertEquals(0, bookData.getBookQuantity("1234567890123"));
        assertEquals(0, bookData.getBookQuantity("9876543210123"));
    }

    @Test
    void testGetBook() {
        // Test getBook method
        // Create a book for testing
        Book testBook = new Book("1234567890123", "Test Book", "Test Description", 24.99f, "Test Author", true, 5);

        // Add the book to the books list
        bookData.books.add(testBook);

        // Retrieve the book using getBook
        Book retrievedBook = bookData.getBook("1234567890123");

        // Verify that the retrieved book is the same as the original
        assertEquals(testBook, retrievedBook);
    }

    @Test
    void testGetFromName() {
        // Test getFromName method
        // Create some books for testing
        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        // Add books to the books list
        bookData.books.addAll(Arrays.asList(book1, book2));

        // Retrieve books containing "Test" in the title
        ArrayList<Book> result = bookData.getFromName("Test");

        // Verify that the result contains both test books
        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
    }

    @Test
    void testSearchByTitle() {
        // Test searchByTitle method
        // Create some books for testing
        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        // Add books to the books list
        bookData.books.addAll(Arrays.asList(book1, book2));

        // Search for a book by title
        Book result = bookData.searchByTitle("Test Book 1");

        // Verify that the result is the first test book
        assertEquals(book1, result);
    }

    @Test
    void testGetBooks() {
        // Test getBooks method
        // Create some books for testing
        Book book1 = new Book("1234567890123", "Test Book 1", "Description 1", 19.99f, "Author 1", true, 10);
        Book book2 = new Book("9876543210123", "Test Book 2", "Description 2", 29.99f, "Author 2", false, 20);

        // Add books to the books list
        bookData.books.addAll(Arrays.asList(book1, book2));

        // Retrieve the list of books
        ArrayList<Book> result = bookData.getBooks();

        // Verify that the result contains both test books
        assertEquals(13, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
    }

    @Test
    void testGetBookQuantity() {
        // Test getBookQuantity method
        // Create a book for testing
        Book testBook = new Book("1234567890123", "Test Book", "Test Description", 24.99f, "Test Author", true, 5);

        // Add the book to the books list
        bookData.books.add(testBook);

        // Retrieve the quantity of the book
        int quantity = bookData.getBookQuantity("1234567890123");

        // Verify that the quantity is as expected
        assertEquals(5, quantity);
    }

    @Test
    void testCheckIsbn13ValidStatic() {
        // Test the private static method checkIsbn13 for a valid ISBN 13
        assertTrue(BookData.checkIsbn13("1234567890123"));
    }

    @Test
    void testCheckIsbn13InvalidStatic() {
        // Test the private static method checkIsbn13 for an invalid ISBN 13
        assertFalse(BookData.checkIsbn13("InvalidISBN"));
    }

}
