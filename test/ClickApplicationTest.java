import Style.LoginPage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.util.Locale.lookup;
//import static jdk.internal.jshell.debug.InternalDebugControl.release;
import static org.testfx.assertions.api.Assertions.assertThat;


public class ClickApplicationTest extends AppTest {
    @Override
    public void start(Stage stage) throws Exception {

    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        new LoginPage(stage);
//    }
//
//    @Test
//    public void testLoginButton() {
//        // Look up the components by their fx:id or type
//
//        TextField emailTextField = lookup("#EmailTextField").query();
//        TextField passwordTextField = lookup("#PasswordTextField").query();
//
//        // Set values for testing
//        interact(() -> {
//            emailTextField.setText("test@example.com");
//            passwordTextField.setText("password123");
//        });
//
//        // Perform the login action
//        clickOn("#LogInBtn");
//
//        // Verify the result using assertions from TestFX
//        // Add assertions based on the expected behavior after login
//        // For example, you can check if a new scene is set on the primaryStage
//        assertThat(primaryStage.getScene()).isNotNull();
//    }
//
//    @Override
//    public void stop() throws Exception {
//        FxToolkit.hideStage();
//        release(new KeyCode[]{}); // add additional key releases if needed
//        release(new MouseButton[]{}); // add additional mouse button releases if needed
//    }
//
//
}
