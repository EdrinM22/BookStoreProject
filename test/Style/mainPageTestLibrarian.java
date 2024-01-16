package Style;

import Staff.Admin;
import Staff.Gender;
import Staff.Librarian;
import Staff.Worker;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
class mainPageTestLibrarian extends ApplicationTest {
    private MainPage mainPage;
    @Start
    public void start(Stage stage) {
        Librarian librarian = new Librarian("Alice Smith", "123-456-7890", "alice@example.com", "1990-01-01", Gender.FEMALE, 50000.0f, "password123", Worker.ACCESSLEVEL.LIBRARIAN, true);
        mainPage = new MainPage(stage, librarian);
        stage.setScene(new Scene(mainPage.getRoot()));
        stage.show();
    }

    @Test
    public void shouldContainLogoutButtonWithCorrectText() {
        verifyThat("#Logout", LabeledMatchers.hasText("Log Out"));
    }



    @Test
    public void shouldContainSearchBar() {
        verifyThat("#Search", TextInputControlMatchers.hasText(""));
    }

    @Test
    public void shouldEnableAddWorkerButtonForAdmin() {
        verifyThat("#AddWorker", NodeMatchers.isDisabled());
    }

    @Test
    public void shouldSearchForBookByTitle() {
        clickOn("#Search").write("The Hobbit").press(KeyCode.ENTER);
        clickOn("#NrBooks").write("5");
        verifyThat("#NrBooks",hasText("5"));
    }


    @Test
    public void shouldEnableAddBookButtonForAdmin() {
        verifyThat("#AddBook", NodeMatchers.isDisabled());
    }

    @Test
    public void shouldDisableAddStockButtonForAdmin() {
        verifyThat("#AddBookStock", NodeMatchers.isDisabled());
    }

    @Test
    public void shouldPurchaseForAdmin() {
        verifyThat("#PurchaseBook", NodeMatchers.isEnabled());
    }

    @Test
    void getRoot() {
        assertNotNull(mainPage.getRoot());
    }

    @Test
    void getRightBook() {
        BorderPane rightBook = mainPage.getRightBook();
        assertNotNull(rightBook);
        verifyThat("#PurchaseBook", LabeledMatchers.hasText("Purchase Books"));
    }

    @Test
    void getRightEmployee() {
        BorderPane rightEmployee = mainPage.getRightEmployee();
        assertNotNull(rightEmployee);
    }
    @Test
    void createMainPage() {
        assertNotNull(mainPage);
        assertNotNull(mainPage.getRoot());
    }
    @Test
    void testAddToStockButtonOpensNewWindow() {
        clickOn("#AddBookStock");
        verifyThat("Add To Stock", NodeMatchers.isVisible());
    }
    @Test
    void EmployeeButtonTest(){
        verifyThat("#Employees",NodeMatchers.isEnabled());
    }
}
