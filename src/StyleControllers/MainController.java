package StyleControllers;

import java.util.ArrayList;

import BookstoreData.*;
import Orders.BuyOrders;
import Staff.*;
import Style.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    private static Double TotalBookPrice = 0.0;
    private static VBox bookInfo;
    private static ArrayList <String> bookIsbns = new ArrayList<String>();
    private static SettingStyles styles = new SettingStyles();
    
    //LogOut
    public static void LogOut(Stage primaryStage){
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.setScene(new Scene(new LoginPage(primaryStage).getRoot(), 800, 600));
    }

    //Add Worker
    public static void addWorker(WorkerData workers, Stage primaryStage, Worker worker){
        workers.newWorkerForm(primaryStage, worker);
    }

    //Add Book
    public static void addBook(BookData books, Stage primaryStage, Worker worker){
        books.newBookForm(primaryStage, worker);
    }

    //Book Table
    public static TableView<Book> bookTable(BookData books, Stage primaryStage, String... args){
        TableView Table = new TableView();

       
            ObservableList<Book> data = FXCollections.observableArrayList(books.getBooks());
            Table.setItems(data);

            for(int i = 0; i < args.length; i++) {
                TableColumn titleCol = new TableColumn(args[i]);
                titleCol.setPrefWidth(200);
                titleCol.setStyle(styles.getTableColumnStyle());
                titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>(args[i]));
                Table.getColumns().add(titleCol);

            }
            
        return Table;
    }

    //Worker Table
    public static TableView<Worker> workerTable(WorkerData workers,Worker worker, Stage primaryStage, String... args){

        TableView Table = new TableView();

        ObservableList<Worker> data ;
            if(worker.getACCESSLEVEL().toString().equals("MANAGER")){
                data = FXCollections.observableArrayList(workers.getLibrarians());
            }
            else{
                data = FXCollections.observableArrayList(workers.getData());
            }

            Table.setItems(data);

            for(int i = 0; i < args.length; i++) {
                TableColumn titleCol = new TableColumn(args[i]);
                titleCol.setStyle(styles.getTableColumnStyle());
                titleCol.setPrefWidth(185);
                titleCol.setCellValueFactory(new PropertyValueFactory<Worker, String>(args[i]));
                
                Table.getColumns().add(titleCol);
            }
        return Table;
    }
}
