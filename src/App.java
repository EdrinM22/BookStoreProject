import javafx.stage.Stage;
import BookstoreData.BookData;
import Staff.*;
import Staff.WorkerData;
import Style.LoginPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class App extends Application
{
    
    @Override
    public void start(Stage primaryStage){

    Scene scene = new Scene(new LoginPage(primaryStage).getRoot(), 800, 600);
        

    try{
        primaryStage.getIcons().add(new Image("file:src/Images/icon.png"));
    }catch(Exception e){
        System.out.println("nuk ka ikone");
    }
        
        
    primaryStage.setTitle("BookStore Program");
    primaryStage.setScene(scene);
    primaryStage.show();
    
}

    public static void main(final String[] args) {
        launch();
    }
}