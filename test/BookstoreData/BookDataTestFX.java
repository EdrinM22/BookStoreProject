package BookstoreData;
import Staff.Admin;
import Staff.Gender;
import Staff.Worker;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;


import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
public class BookDataTestFX extends ApplicationTest {

    private BookData bookData;
    @Override
    public void start(Stage stage) {
        bookData = new BookData();

        bookData.newBookForm(stage,new Admin("Sample", "123456789", "sample@example.com", 2000.0F, "20/2/2000", Gender.MALE, "password", Worker.ACCESSLEVEL.LIBRARIAN));



    }
    @Test
    void testAddingBook() {
        clickOn("#titleField").write("Hairy Potter");
        clickOn("#authorField").write("J.K. Bowling");
        clickOn("#isbnField").write("1234567890123");
        clickOn("#priceField").write("20");
        clickOn("#paperbackRadio");
        clickOn("#descriptionField").write("rando description");

        verifyThat("#submitButton", isEnabled());


    }

    @Test
    void testFormFieldsContainCorrectTextAfterInput() {
        String testTitle = "Hairy Potter";
        clickOn("#titleField").write(testTitle);
        verifyThat("#titleField", hasText(testTitle));

        String testAuthor = "J.K. Bowling";
        clickOn("#authorField").write(testAuthor);
        verifyThat("#authorField", hasText(testAuthor));

        String testISBN = "1234567890123";
        clickOn("#isbnField").write(testISBN);
        verifyThat("#isbnField", hasText(testISBN));

        String testPrice = "20";
        clickOn("#priceField").write(testPrice);
        verifyThat("#priceField", hasText(testPrice));

        String testDescription = "rando description";
        clickOn("#descriptionField").write(testDescription);
        verifyThat("#descriptionField", hasText(testDescription));

        clickOn("#paperbackRadio");
        verifyThat("#paperbackRadio", isEnabled());


        verifyThat("#submitButton", isEnabled());



    }

}

