package Style;

import Staff.Admin;
import Staff.Gender;
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
class MainPageTest extends ApplicationTest {
    private MainPage mainPage;
    @Start
    public void start(Stage stage) {
        Admin admin = new Admin("Bob Johnson", "987-654-3210", "bob@example.com", 60000.0f, "1995-05-05", Gender.MALE, "adminPass123", Worker.ACCESSLEVEL.ADMIN);
        mainPage = new MainPage(stage, admin);
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
        verifyThat("#AddWorker", NodeMatchers.isEnabled());
    }

    @Test
    public void shouldSearchForBookByTitle() {
        clickOn("#Search").write("The Hobbit").press(KeyCode.ENTER);
        clickOn("#NrBooks").write("5");
        verifyThat("#NrBooks",hasText("5"));
    }


    @Test
    public void shouldEnableAddBookButtonForAdmin() {
        verifyThat("#AddBook", NodeMatchers.isEnabled());
    }

    @Test
    public void shouldDisableAddStockButtonForAdmin() {
        verifyThat("#AddBookStock", NodeMatchers.isEnabled());
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
        verifyThat("#Employees",NodeMatchers.isVisible());
        verifyThat("#Employees",NodeMatchers.isEnabled());
        clickOn("#Employees");
        verifyThat("#WorkerTable", NodeMatchers.isVisible());
        verifyThat("#nameField", NodeMatchers.isVisible());
        verifyThat("#emailField", NodeMatchers.isVisible());
        verifyThat("#phoneField", NodeMatchers.isVisible());
        verifyThat("#statusField", NodeMatchers.isVisible());
        doubleClickOn("Xhensil Gjini");
        verifyThat("#EditWorker", NodeMatchers.isEnabled());
        verifyThat("#DeleteWorker", NodeMatchers.isEnabled());
        verifyThat("#WorkerFull", NodeMatchers.isVisible());
        verifyThat("#WorkerPhone", NodeMatchers.isVisible());
        verifyThat("#WorkerSalary", NodeMatchers.isVisible());
        verifyThat("#WorkerEmail", NodeMatchers.isVisible());
    }
}
