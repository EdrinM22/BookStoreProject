package Style;

import BookstoreData.Book;
import Staff.Gender;
import Staff.Librarian;
import Staff.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class MainPageTest extends ApplicationTest {

    private MainPage mainPage;
    @Start
    public void start(Stage stage) {
        Librarian librarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        mainPage = new MainPage(stage, librarian);
        stage.setScene(new Scene(mainPage.getRoot(), 800, 600));
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
    public void shouldEnableAddBookButtonForAdmin() {
        verifyThat("#AddBook", NodeMatchers.isEnabled());
    }

    @Test
    public void shouldDisableAddBookButtonForLibrarian() {
        verifyThat("#AddBook", NodeMatchers.isDisabled());
    }

    @Test
    public void shouldSearchForBookByTitle() {
        clickOn("#Search").write("as").press(KeyCode.ENTER);
    }

}
