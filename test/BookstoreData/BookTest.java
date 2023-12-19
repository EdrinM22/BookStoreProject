package BookstoreData;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("1234567890123", "Test Book", "Description", 29.99f, "Test Author", true, 10);
        book.addGenres(Book.Genre.FANTASY, Book.Genre.MYSTRERY);
    }

    @Test
    void testGetIsbn13() {
        assertEquals("1234567890123", book.getIsbn13());
    }

    @Test
    void testGetTitle() {
        assertEquals("Test Book", book.getTitle());
    }

    @Test
    void testGetAuthor() {
        assertEquals("Test Author", book.getAuthor());
    }


    @Test
    void testGetStock() {
        assertEquals("10", book.getStock());
    }

    @Test
    void testGetPaperBack() {
        assertEquals("true", book.getPaperBack());
    }

    @Test
    void testGetPrice() {
        assertEquals("29.99", book.getPrice());
    }

    @Test
    void testSetIsbn13() {
        book.setIsbn13("9876543210987");
        assertEquals("9876543210987", book.getIsbn13());
    }

    @Test
    void testSetTitle() {
        book.setTitle("New Test Book");
        assertEquals("New Test Book", book.getTitle());
    }

    @Test
    void testSetAuthor() {
        book.setAuthor("New Test Author");
        assertEquals("New Test Author", book.getAuthor());
    }


    @Test
    void testSetStock() {
        book.setStock(15);
        assertEquals("15", book.getStock());
    }

    @Test
    void testSetPaperBack() {
        book.setPaperBack(false);
        assertEquals("false", book.getPaperBack());
    }

    @Test
    void testSetPrice() {
        book.setPrice(39.99f);
        assertEquals("39.99", book.getPrice());
    }
    //to be done later --> change so set price cannot get negative value
    @Test
    void testSetPriceNegative() {
        book.setPrice(-39.99f);
        assertEquals("Can't be negative", book.getPrice());
    }
}