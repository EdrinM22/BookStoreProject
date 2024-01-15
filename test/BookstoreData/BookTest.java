package BookstoreData;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookTest {
    @Test
    void testSetDescription() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.setDescription("New Description");
        assertEquals("New Description", book.getDescription());
    }

    @Test
    void testSetPaperback() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.setPaperback(false);
        assertFalse(book.isPaperback());
    }

    @Test
    void testSetPrice() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.setPrice(29.99f);
        assertEquals("29.99", book.getPrice());
    }

    @Test
    void testGetIsbn13() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertEquals("1234567890123", book.getIsbn13());
    }

    @Test
    void testGetTitle() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertEquals("Test Book", book.getTitle());
    }

    @Test
    void testGetAuthor() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertEquals("Author", book.getAuthor());
    }

    @Test
    void testGetDescription() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertEquals("Description", book.getDescription());
    }

    @Test
    void testIsPaperback() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertTrue(book.isPaperback());
    }

    @Test
    void testGetGenresEmpty() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertTrue(book.getGenres().isEmpty());
    }

    @Test
    void testGetGenresNonEmpty() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.addGenres(Book.Genre.MYSTRERY, Book.Genre.ACTION);
        assertFalse(book.getGenres().isEmpty());
    }


    @Test
    void testGetGenres() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.addGenre(Book.Genre.MYSTRERY);
        book.addGenre(Book.Genre.ACTION);
        assertEquals(2, book.getGenres().size());
    }

    @Test
    void testAddGenre() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.addGenre(Book.Genre.MYSTRERY);
        assertEquals(1, book.getGenres().size());
    }

    @Test
    void testAddGenres() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.addGenres(Book.Genre.MYSTRERY, Book.Genre.ACTION);
        assertEquals(2, book.getGenres().size());
    }

    @Test
    void testRemoveStock() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.removeStock(5);
        assertEquals(5, book.getStockInt());
    }

    @Test
    void testAddStock() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.addStock(5);
        assertEquals(15, book.getStockInt());
    }

    @Test
    void testGetStockInt() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertEquals(10, book.getStockInt());
    }


    @Test
    void testSetIsbn13() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.setIsbn13("9876543210987");
        assertEquals("9876543210987", book.getIsbn13());
    }

    @Test
    void testSetTitle() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.setTitle("New Title");
        assertEquals("New Title", book.getTitle());
    }

    @Test
    void testSetAuthor() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.setAuthor("New Author");
        assertEquals("New Author", book.getAuthor());
    }


    @Test
    void testToString() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        String expected = "Test Book by Author, 19.99 leke";
        assertEquals(expected, book.toString());
    }
    @Test
    void testGetStock() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertEquals("10", book.getStock());
    }


    @Test
    void testGetPaperBack() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        assertEquals("true", book.getPaperBack());
    }
    @Test
    void testSetPaperbackString() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.setPaperBack(false);
        assertEquals("false", book.getPaperBack());
    }
}
