package Style;

import BookstoreData.Book;
import Staff.Gender;
import Staff.Librarian;
import Staff.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class MainPageTest extends ApplicationTest {

    private MainPage mainPage;
    private Worker worker;
    @Override
    public void start(Stage stage) {
        Librarian tempLibrarian = new Librarian("John Doe", "123-456-7890", "john@example.com", "01-01-1990",
                Gender.MALE, 5000.0f, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        mainPage = new MainPage(stage, tempLibrarian);
        stage.setScene(new Scene(mainPage.getRoot(), 800, 600));
        stage.show();
        stage.toFront();
    }

    @Test
    void testPurchaseBookPane() {
        Book book = new Book("1234567890123", "Test Book", "Description", 19.99f, "Author", true, 10);
        book.setIsbn13("9876543210987"); // Initialize Book
        GridPane purchaseBookPane = mainPage.getPurchaseBookPane(book);


        mainPage.getRoot().setCenter(purchaseBookPane);


        clickOn("#NrBooks").write("5");
        press(javafx.scene.input.KeyCode.ENTER);

        Label totalLabel = lookup("#TotalLabel").queryAs(Label.class);

        verifyThat(totalLabel, hasText("Total: " + 99.95f));
    }


}
