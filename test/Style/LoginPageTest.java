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
        assertThat(lookup("#Login").queryAs(Text.class)).isNotNull();
        assertThat(lookup("#Email").queryAs(Label.class)).isNotNull();
        assertThat(lookup("#Password").queryAs(Label.class)).isNotNull();
        assertThat(lookup("#email").queryAs(TextField.class)).isNotNull();
        assertThat(lookup("#password").queryAs(PasswordField.class)).isNotNull();
        assertThat(lookup("#enter").queryButton()).isNotNull();
    }

    @Test
    void testTextFieldInteraction() {
        clickOn("#email").write("test@example.com");
        clickOn("#password").write("password123");

        assertThat(lookup("#email").<TextField>query()).hasText("test@example.com");
        assertThat(lookup("#password").<PasswordField>query()).hasText("password123");
    }

    @Test
    void testLoginButtonClick() {

        clickOn("#email").write("Admin");
        clickOn("#password").write("1");

        clickOn("#enter");

    }
}
