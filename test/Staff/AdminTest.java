package Staff;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AdminTest {
    @Test
    void testConstructor() {
        Admin admin = new Admin("Bob Johnson", "987-654-3210", "bob@example.com", 60000.0f, "1995-05-05", Gender.MALE, "adminPass123", Worker.ACCESSLEVEL.ADMIN);

        assertEquals("Bob Johnson", admin.getFullName());
        assertEquals("987-654-3210", admin.getPhone());
        assertEquals("bob@example.com", admin.getEmail());
        assertEquals(60000.0f, admin.getSalary());
        assertEquals("1995-05-05", admin.getDateOfBirth());
        assertEquals(Gender.MALE, admin.getGender());
        assertEquals("adminPass123", admin.getPassword());
        assertEquals(Worker.ACCESSLEVEL.ADMIN, admin.getACCESSLEVEL());
    }
    @Test
    void testInteract() {
        Admin admin = new Admin("Bob Johnson", "987-654-3210", "bob@example.com", 60000.0f, "1995-05-05", Gender.MALE, "adminPass123", Worker.ACCESSLEVEL.ADMIN);
        assertDoesNotThrow(admin::interact);
    }
}
