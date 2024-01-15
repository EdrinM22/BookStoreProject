package Staff;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class WorkerDataTestFX extends ApplicationTest {

    private WorkerData workerData;


    @Override
    public void start(Stage stage) {
        workerData = new WorkerData();

        workerData.newWorkerForm(stage, new Admin("Sample", "123456789", "sample@example.com", 2000.0F, "20/2/2000", Gender.MALE, "password", Worker.ACCESSLEVEL.LIBRARIAN));

    }
    @Test
    void testAddingWorker() {
        clickOn("#nameField").write("John Doe");
        clickOn("#emailField").write("john@example.com");
        clickOn("#phoneField").write("1234567890");
        clickOn("#salaryField").write("50000");
        clickOn("#passwordField").write("password123");
         clickOn("#datePicker").write("01/01/2000");
        clickOn("#maleRadio");
        clickOn("#locationChoiceBox").clickOn("Librarian");

        verifyThat("#submitButton", isEnabled());

    }

    @Test
    void testFormFieldsContainCorrectTextAfterInput() {
        String testName = "Jane Doe";
        clickOn("#nameField").write(testName);
        verifyThat("#nameField", hasText(testName));

        String testEmail = "jane@example.com";
        clickOn("#emailField").write(testEmail);
        verifyThat("#emailField", hasText(testEmail));

        String testPhone = "9876543210";
        clickOn("#phoneField").write(testPhone);
        verifyThat("#phoneField", hasText(testPhone));

        String testSalary = "60000";
        clickOn("#salaryField").write(testSalary);
        verifyThat("#salaryField", hasText(testSalary));

        String testPassword = "password123";
        clickOn("#passwordField").write(testPassword);
        verifyThat("#passwordField", hasText(testPassword));

        String testDate = "01/01/2000";
        clickOn("#datePicker").write(testDate);

        clickOn("#maleRadio");
        verifyThat("#maleRadio", isEnabled());

        clickOn("#locationChoiceBox").clickOn("Librarian");
        verifyThat("#locationChoiceBox", isEnabled());

        verifyThat("#submitButton", isEnabled());



    }

}