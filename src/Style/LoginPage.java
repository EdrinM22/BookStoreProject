package Style;

import java.io.IOException;

import BookstoreData.BookData;
import Staff.Admin;
import Staff.Librarian;
import Staff.Manager;
import Staff.Worker;
import Staff.WorkerData;
import StyleControllers.LoginController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage{

    private BorderPane root;
    private Stage primaryStage;

    public LoginPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createLoginPage();
    }

    private void createLoginPage() {
        SettingStyles styles = new SettingStyles();
        root = new BorderPane();
        root.setStyle(styles.getLoginRootStyle());

        HBox top = new HBox();
        Text LoginText = new Text("Login");
        LoginText.setStyle(styles.getLoginTextStyle());
        top.getChildren().add(LoginText);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new javafx.geometry.Insets(50, 0, 0, 0));
        root.setTop(top);

        HBox bottom = new HBox();
        Button LogInBtn = new Button("Enter");
        bottom.getChildren().add(LogInBtn);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new javafx.geometry.Insets(0, 0, 150, 0));
        LogInBtn.setPrefSize(100, 50);
        LogInBtn.setStyle(styles.getLoginBtnStyle());
        root.setBottom(bottom);

    
        GridPane center = new GridPane();
        center.setAlignment(Pos.CENTER);
        Label EmailLabel = new Label("Email");
        EmailLabel.setStyle(styles.getLoginLabelStyle());
        Label PasswordLabel = new Label("Password");
        PasswordLabel.setStyle(styles.getLoginLabelStyle());
        TextField EmailTextField = new TextField();
        PasswordField PasswordTextField = new PasswordField();
        center.add(EmailLabel, 0, 0);
        center.add(EmailTextField, 0, 1);
        center.add(PasswordLabel, 0, 2);
        center.add(PasswordTextField, 0, 3);
        EmailTextField.setPrefWidth(300);
        PasswordTextField.setPrefWidth(300);
        EmailTextField.setStyle(styles.getLoginTextFieldStyle());
        PasswordTextField.setStyle(styles.getLoginTextFieldStyle());
        root.setCenter(center);

        LogInBtn.setOnAction(e -> LoginController.login(EmailTextField.getText(), PasswordTextField.getText(), primaryStage, center));
    }

    public BorderPane getRoot(){
        return root;
    }
    
    

}