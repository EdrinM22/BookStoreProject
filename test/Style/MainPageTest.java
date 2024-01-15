package Style;

import Staff.Gender;
import Staff.Librarian;
import Staff.Worker;
import Style.MainPage;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class MainPageTest {

    private MainPage mainPage;
    private Worker worker; // Replace with actual Worker initialization

    @Start
    public void start(Stage stage) {
        // Initialize your Worker object and any other dependencies here
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



    // Additional tests go here
}