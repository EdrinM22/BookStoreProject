package Style;

import BookstoreData.Book;
import Staff.Gender;
import Staff.Librarian;
import Staff.Manager;
import Staff.Worker;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
class MainPageTest extends ApplicationTest {

    private MainPage mainPage;
    @Start
    public void start(Stage stage) {
        Manager manager = new Manager("John Doe", "123-456-7890", "john.doe@example.com", 50000.0f, "1990-01-01", Gender.MALE, "password123", Worker.ACCESSLEVEL.MANAGER, true, false);
        mainPage = new MainPage(stage, manager);
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
    public void shouldEnableAddWorkerButtonForManager() {
        verifyThat("#AddWorker", NodeMatchers.isDisabled());
    }

    @Test
    public void shouldSearchForBookByTitle() {
        clickOn("#Search").write("The Hobbit").press(KeyCode.ENTER);
    }


    @Test
    public void shouldEnableAddBookButtonForManager() {
        verifyThat("#AddBook", NodeMatchers.isEnabled());
    }

    @Test
    public void shouldDisableAddStockButtonForManager() {
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

}
