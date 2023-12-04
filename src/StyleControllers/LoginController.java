package StyleControllers;

import java.net.SocketTimeoutException;

import Staff.*;
import Style.MainPage;
import Style.SettingStyles;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginController{

    private static Label wrongPassword;
    private static Label wrongEmail;

    public static void login(String email, String password, Stage primaryStage, GridPane center){

        WorkerData workerData = new WorkerData();
        SettingStyles styles = new SettingStyles();
            
        boolean emailExists=false;
        Worker temp = workerData.getWorkerFromEmail(email);
        center.getChildren().remove(wrongPassword);
        center.getChildren().remove(wrongEmail);
        if(temp!=null){ 
            emailExists=true;
        }
        if(emailExists){
                
        if(temp.getPassword().equals(password)){
            Worker worker;
                    
            if(temp.getACCESSLEVEL().equals(Worker.ACCESSLEVEL.ADMIN)){
                worker = new Admin(temp.getFullName(), temp.getPhone(), temp.getEmail(), temp.getSalary(), temp.getDateOfBirth(), temp.getGender(),temp.getPassword(), temp.getACCESSLEVEL());
            }else if(temp.getACCESSLEVEL().equals(Worker.ACCESSLEVEL.LIBRARIAN)){
                worker = new Librarian(temp.getFullName(), temp.getPhone(), temp.getEmail(), temp.getDateOfBirth(),  temp.getGender(),temp.getSalary(), temp.getPassword(), temp.getACCESSLEVEL(), ((Librarian) temp).isPermitionToBill());
            }else{
                worker = new Manager(temp.getFullName(), temp.getPhone(), temp.getEmail(), temp.getSalary(), temp.getDateOfBirth(), temp.getGender(), temp.getPassword(), temp.getACCESSLEVEL(), ((Manager) temp).isPermitionToPurchse(), ((Manager) temp).isCheckLibrarians());
            } 
            Scene scene = new Scene(new MainPage(primaryStage, worker).getRoot(), 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
        }
            else {
                wrongPassword = new Label("Wrong Password");
                wrongPassword.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
                center.add(wrongPassword, 0, 4);
            }

        }else{
            wrongEmail = new Label("Email Doesn't Exist");
            wrongEmail.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
            center.add(wrongEmail, 0, 4);
        }
    }
            
} 

