package StyleControllers;

import BookstoreData.Book;
import BookstoreData.BookData;
import Staff.Worker;
import Staff.WorkerData;
import Style.LoginPage;
import Style.SettingStyles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController {

    private static final Double TotalBookPrice = 0.0;
    private static VBox bookInfo;
    private static final ArrayList <String> bookIsbns = new ArrayList<String>();
    private static final SettingStyles styles = new SettingStyles();
    
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
