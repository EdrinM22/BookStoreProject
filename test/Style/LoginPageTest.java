package Style;

import Style.LoginPage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class LoginPageTest extends ApplicationTest {

    private LoginPage loginPage;

    @Override
    public void start(Stage stage) {
        loginPage = new LoginPage(stage);
        stage.setScene(new Scene(loginPage.getRoot(), 400, 600));
        stage.show();
        stage.toFront();
    }

    @Test
    void testLoginPageInitialization() {
        // Check if all elements are present and not null
        assertThat(lookup("#Login").queryAs(Text.class)).isNotNull();
        assertThat(lookup("#Email").queryAs(Label.class)).isNotNull();
        assertThat(lookup("#Password").queryAs(Label.class)).isNotNull();
        assertThat(lookup("#email").queryAs(TextField.class)).isNotNull();
        assertThat(lookup("#password").queryAs(PasswordField.class)).isNotNull();
        assertThat(lookup("#enter").queryButton()).isNotNull();
    }

    @Test
    void testTextFieldInteraction() {
        // Typing into the text fields
        clickOn("#email").write("test@example.com");
        clickOn("#password").write("password123");

        // Check if the text fields contain the typed text
        assertThat(lookup("#email").<TextField>query()).hasText("test@example.com");
        assertThat(lookup("#password").<PasswordField>query()).hasText("password123");
    }

    @Test
    void testLoginButtonClick() {
        // Type in the credentials
        clickOn("#email").write("Admin");
        clickOn("#password").write("1");

        // Click the login button
        clickOn("#enter");

        // Here, you should include assertions or verifications for what happens when the button is clicked.
        // This could be a change of scene, a dialog pop-up, or a method call.
        // It will depend on the specific behavior of your application.
    }
}
