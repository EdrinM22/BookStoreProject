import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.concurrent.TimeoutException;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class AppTest {

    @Test
    public void testAppStarts(FxRobot robot) throws TimeoutException {
        // Arrange
        App app = new App();

        // Act
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App::new);

        // Assert
        assertThat(robot.lookup("#enter").queryButton()).hasText("Enter");
        // Add more assertions based on your UI elements
    }

    @Test
    public void testLoginPage(FxRobot robot) throws TimeoutException {
        // Arrange
        App app = new App();

        // Act
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App::new);

        // Interact with the UI using robot
        robot.clickOn("#enter");

        // Assert
        // Add assertions based on the behavior you expect after clicking the login button
    }

    // Add more test cases for different scenarios and components

    @Test
    public void testIconLoading() {
        // Add a test for icon loading
    }
}
