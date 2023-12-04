package Style;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import BookstoreData.Book;
import BookstoreData.BookData;
import Orders.BillData;
import Orders.BuyOrders;
import Orders.PurchaseOrders;
import Staff.Admin;
import Staff.Gender;
import Staff.Librarian;
import Staff.Manager;
import Staff.Worker;
import Staff.WorkerData;
import Staff.Worker.ACCESSLEVEL;
import StyleControllers.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.VideoTrack;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainPage{

    private BorderPane root;
    private HBox top;
    private VBox PersonalInfo;
    private Stage primaryStage;
    private Button addBookBtn;
    private Button addWorkerBtn;
    private SettingStyles styles;
    private VBox center;
    private Worker worker;
    private BookData books;
    private WorkerData workers;
    private TableView<Book> bookTableView;
    private TableView<Worker> workerTableView;
    private BorderPane right;
    private VBox BookInfoHolder;
    private Button addBookToStockBtn;
    private Double TotalBookPrice = 0.0;
    ArrayList <String> bookIsbns = new ArrayList<String>();
    ArrayList <Double> prices = new ArrayList<Double>();
    private HBox TotalLabelHbox;
    private VBox EmployeeInfoHolder;
    private Button purchaseBookBtn;
    private Label workerFullName;
    private Label workerEmail;
    private Label workerAccessLevel;
    private Label workerSalary;
    private Label workerPhoneNumber;
    private boolean permitionToPurchase;
    private boolean permitionToCheckLib;
    private boolean permitionToBill;
    private BillData billData;


    public MainPage(Stage primaryStage, Worker worker) {
        this.primaryStage = primaryStage;
        root = new BorderPane();
        this.worker=worker;
        this.books = new BookData();
        this.workers = new WorkerData();
        this.billData= new BillData();

        if(!(worker instanceof Librarian)){
           int a=0;
           String names="\n";
        for (Book b : books.getBooks()) {
            if(Integer.parseInt(b.getStock()) < 5){
                System.out.println(b.getStock());
                names+= b.getTitle()+"\n";
                a++;
            }
        }
    if(a!=0) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Low Stock");
        alert.setHeaderText("Low Stock");
        alert.setContentText("There are books with low stock"+names);
        alert.showAndWait();
    }}
        try {
            createMainPage();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("File not found");
        }
    }

    private void createMainPage() throws FileNotFoundException {
        //System.out.println(worker.getClass());
        styles = new SettingStyles();
        root.setStyle(styles.getLoginRootStyle());
        primaryStage.setMinHeight(850);
        primaryStage.setMinWidth(1635);
        
        //Left Pane
        PersonalInfo = new VBox(25);
        Image profileImage;
        if(worker.getGender().equals(Gender.FEMALE)){
            profileImage = new Image(new FileInputStream("src\\Images\\Woman.png"));
        }else{
            profileImage = new Image(new FileInputStream("src\\Images\\Man.png"));
        }

        Circle circle = new Circle(125, 125, 125);
        circle.setFill(new javafx.scene.paint.ImagePattern(profileImage));

        Text name = new Text(worker.getFullName());
        name.setStyle(styles.getMainPagePersonalInfoStyle());
        Text email = new Text(worker.getEmail());
        email.setStyle(styles.getMainPagePersonalInfoStyle());
        Text phone = new Text(worker.getPhone());
        phone.setStyle(styles.getMainPagePersonalInfoStyle());
        Text status = new Text(worker.getACCESSLEVEL().toString());
        status.setStyle(styles.getMainPagePersonalInfoStyle());
        
        PersonalInfo.getChildren().addAll(circle, name, email, phone, status);
        PersonalInfo.setStyle(styles.getMainPageLeftPaneStyle());
        root.setLeft(PersonalInfo);

        //Top Pane
        top = new HBox(500);
        Button LogOutBtn = new Button("Log Out");
        LogOutBtn.setStyle(styles.getLogOutBtnStyle());

        HBox searchBox = new HBox(15);
        TextField searchBar = new TextField();
        searchBar.setStyle(styles.getSearchBarStyle());
        searchBar.setPromptText("Search");
        searchBar.setPrefHeight(50);
        searchBar.setPrefWidth(500);

        ChoiceBox<String> searchList = new ChoiceBox<>();
        searchList.getItems().addAll("Title", "Author", "Genre");
        searchList.setValue("Title");
        searchList.setStyle(styles.getSearchListStyle());
        searchBox.getChildren().addAll(searchList, searchBar);

        top.getChildren().addAll(searchBox, LogOutBtn);
        top.setAlignment(Pos.TOP_RIGHT);
        top.setPadding(new javafx.geometry.Insets(10, 10, 10, 0));
        root.setTop(top);
       

        //Center Pane
        // center = new VBox(20);
        // center.setPadding(new javafx.geometry.Insets(0, 0, 10, 0));
        TabPane tabPane = new TabPane();

        Tab BookTab = new Tab("Books");
        BookTab.setStyle(styles.getBookTableStyle());
        BookTab.setClosable(false);
        tabPane.getTabs().add(BookTab);
        //bookTableView = showTable(0, "Title", "Author", "Price", "Stock","Isbn13","Paperback");
        bookTableView = MainController.bookTable(books, primaryStage, "Title", "Author", "Price", "Stock","Isbn13","Paperback");
        


        HBox bookBottomPane = new HBox(20);
        bookBottomPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 0));
        bookBottomPane.setStyle(styles.getBtnPane());
        addBookBtn = new Button("Add Book");
        addBookBtn.setStyle(styles.getLogOutBtnStyle());
        addBookToStockBtn = new Button("Add To Stock");
        addBookToStockBtn.setStyle(styles.getLogOutBtnStyle());
        
        if(worker.getACCESSLEVEL().toString().equals("LIBRARIAN")) {
            addBookBtn.setDisable(true);
            addBookToStockBtn.setDisable(true);
        }
        bookBottomPane.getChildren().addAll(addBookBtn, addBookToStockBtn);
        bookBottomPane.setAlignment(Pos.BOTTOM_RIGHT);

        BorderPane bookBorderPane = new BorderPane();
        bookBorderPane.setCenter(bookTableView);
        bookBorderPane.setBottom(bookBottomPane);

        BookTab.setContent(bookBorderPane);

        //Employee Tab
    
        Tab EmployeeTab = new Tab("Employees");
        EmployeeTab.setStyle(styles.getBookTableStyle());
        EmployeeTab.setClosable(false);

        if(worker.getACCESSLEVEL().toString().equals("LIBRARIAN")){
            EmployeeTab.setDisable(true);
        }
        if(worker instanceof Manager){
            boolean isPromited = ((Manager) worker).isCheckLibrarians();
            if(!isPromited){
                EmployeeTab.setDisable(true);
            }
        }
        tabPane.getTabs().add(EmployeeTab);

        workerTableView = MainController.workerTable(workers, worker, primaryStage, "FullName", "Email", "Phone", "Status","dateOfBirth", "salary");

        HBox workerBottomPane = new HBox(20);
        workerBottomPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 0));
        workerBottomPane.setStyle(styles.getBtnPane());

        addWorkerBtn = new Button("Add Worker");

        if(!worker.getACCESSLEVEL().toString().equals("ADMIN")){
                    addWorkerBtn.setDisable(true);
        }

        addWorkerBtn.setStyle(styles.getLogOutBtnStyle());
        workerBottomPane.getChildren().add(addWorkerBtn);
        workerBottomPane.setAlignment(Pos.BOTTOM_RIGHT);
        
        
        BorderPane workerBorderPane = new BorderPane();
        workerBorderPane.setCenter(workerTableView);
        workerBorderPane.setBottom(workerBottomPane);
        
        EmployeeTab.setContent(workerBorderPane);
        
        // center.getChildren().add(tabPane);
        root.setCenter(tabPane);

        //Right Pane
        root.setRight(getRightBook());

        
        //Button Action

        searchBar.setOnKeyPressed(e -> {
            if(KeyCode.ENTER == e.getCode()){
                String choice = searchList.getValue();
                String content = searchBar.getText();
                System.out.println(content);
                if(choice.equals("Title")){
                    System.out.println("hyri");
                    Book book = books.searchByTitle(content);
                    System.out.println("U gjet libri" + book.getTitle());
                    if(book != null){
                        BookInfoHolder.getChildren().add(getPurchaseBookPane(book));
                    }
                }else if(choice.equals("Author")){

                }else{

                }

            }
        });

        LogOutBtn.setOnAction(e -> MainController.LogOut(primaryStage));

        addWorkerBtn.setOnAction(e-> MainController.addWorker(workers, primaryStage , worker));

        addBookBtn.setOnAction(e-> MainController.addBook(books, primaryStage, worker));

        
        bookTableView.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                Book book = bookTableView.getSelectionModel().getSelectedItem();
                BookInfoHolder.getChildren().add(getPurchaseBookPane(book));
            }
        });
        
        workerTableView.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                if(workerTableView.getSelectionModel().getSelectedItem().getACCESSLEVEL().toString().equals("ADMIN")){
                    return;
                }
                Worker employee = workerTableView.getSelectionModel().getSelectedItem();
                EmployeeInfoHolder.getChildren().clear();
                EmployeeInfoHolder.getChildren().add(getEmployeePane(employee));
            }
        });
        
        addBookToStockBtn.setOnAction(e -> {
    
            ArrayList<Integer> quantity = new ArrayList<>();

            for (Node node: BookInfoHolder.getChildren()) {
                GridPane g=(GridPane) node;
                for (Node el: g.getChildren()) {
                   
                    
                    if(el instanceof TextField)
                    {
                        TextField t=(TextField) el;
                        quantity.add(Integer.parseInt(t.getText()));  
                    }
                }
            }

            books.addBooksToStock(new BuyOrders(bookIsbns, quantity,TotalBookPrice,worker.getFullName()));
            BookInfoHolder.getChildren().clear();
            primaryStage.setScene(new Scene(new MainPage(primaryStage, worker).getRoot(), 800, 600));
            primaryStage.setFullScreen(true);
       });

       
       purchaseBookBtn.setOnAction(e -> {
        System.out.println("purchase");
        ArrayList<Integer> quantity = new ArrayList<>();
        for (Node node: BookInfoHolder.getChildren()) {
            GridPane g=(GridPane) node;
            for (Node el: g.getChildren()) {
               int q=0;
                
                if(el instanceof TextField)
                {
                    TextField t=(TextField) el;
                    q = Integer.parseInt(t.getText());
                    
                    quantity.add(q);     
                }
                if(q>books.getBookQuantity(bookIsbns.get(BookInfoHolder.getChildren().indexOf(node)))){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Quantity is greater than the stock");
                    alert.showAndWait();
                    return;
                }
               
            }
            
        }
        PurchaseOrders purchase = new PurchaseOrders(bookIsbns, quantity, TotalBookPrice, worker.getFullName());
        books.removeBooksFromStock(purchase);
        worker.addPurchases(purchase.getTotalPrice());
        workers.rewirteFile();
        BookInfoHolder.getChildren().clear();
        primaryStage.setScene(new Scene(new MainPage(primaryStage, worker).getRoot(), 800, 600));
        primaryStage.setFullScreen(true);
        });


        BookTab.setOnSelectionChanged(e -> {
            if(BookTab.isSelected()){
                right.getChildren().clear();
                right.setRight(getRightBook());
            }
        });

        EmployeeTab.setOnSelectionChanged(e -> {
            if(EmployeeTab.isSelected()){
                right.getChildren().clear();
                right.setRight(getRightEmployee());
            }
        });
    }
    
    public BorderPane getRoot() {
        return root;
    }  


    public BorderPane getRightBook() {

        right = new BorderPane();
        BookInfoHolder = new VBox(10);

        right.setCenter(BookInfoHolder);

        VBox BottomVBox = new VBox(10);
        TotalLabelHbox = new HBox(10);
        HBox purchaseBookBtnHolder = new HBox(20);
        BottomVBox.getChildren().addAll(TotalLabelHbox, purchaseBookBtnHolder);
        purchaseBookBtnHolder.setStyle(styles.getBtnPane());
        purchaseBookBtn = new Button("Purchase Books");
        if(worker instanceof Librarian){
            worker = (Librarian) worker;
            boolean hasAccess = ((Librarian) worker).isPermitionToBill();
            if(!hasAccess){
                purchaseBookBtn.setDisable(true);
            }
        }
        if(worker instanceof Manager){
            worker = (Manager) worker;
            boolean hasAccess = ((Manager) worker).isPermitionToPurchse();
            if(!hasAccess){
                addBookToStockBtn.setDisable(true);
            }
        }
        purchaseBookBtn.setStyle(styles.getLogOutBtnStyle());
        purchaseBookBtnHolder.setAlignment(Pos.BOTTOM_CENTER);
        purchaseBookBtnHolder.getChildren().add(purchaseBookBtn);
        right.setBottom(BottomVBox);
        return right;
    }

    public BorderPane getRightEmployee() {

        right = new BorderPane();
        EmployeeInfoHolder = new VBox(10);

        right.setCenter(EmployeeInfoHolder);

        
        return right;
    }


    public GridPane getPurchaseBookPane(Book book){
        
        GridPane grid = new GridPane();
        grid.setStyle(styles.getSalesGridPane());
        grid.setPrefHeight(100);
        grid.setPrefWidth(300);
        grid.setHgap(20);
        grid.setVgap(20);

        Label bookISBN = new Label(book.getIsbn13());
        bookIsbns.add(book.getIsbn13());
        prices.add(Double.parseDouble(book.getPrice()));
        bookISBN.setStyle(styles.getSalesLabel());
        Label bookPrice = new Label("" + book.getPrice());
        bookPrice.setStyle(styles.getSalesLabel());
        TextField nrBooks = new TextField();
        nrBooks.setPrefHeight(20);
        nrBooks.setPrefWidth(50);

        nrBooks.setOnKeyPressed(e -> {
            Label total = new Label();
            total.setStyle(styles.getSalesLabel());
            grid.getChildren().remove(total);
            if(KeyCode.ENTER == e.getCode()){
                total.setText("" + (Double.parseDouble(bookPrice.getText()) * Double.parseDouble(nrBooks.getText())));
                TotalBookPrice += Double.parseDouble(total.getText());
                System.out.println(TotalBookPrice);
                grid.add(total, 1, 1);
                Label totalLabel = new Label("Total: " + TotalBookPrice);
                totalLabel.setStyle(styles.getSalesLabel());
                if(TotalLabelHbox.getChildren().size() != 0){
                    TotalLabelHbox.getChildren().clear();;
                }
                TotalLabelHbox.getChildren().add(totalLabel);
            }
        });
        
        grid.add(bookISBN, 0, 0);
        grid.add(bookPrice, 1, 0);
        grid.add(nrBooks, 0, 1);


        return grid;
    }


    public GridPane getEmployeePane(Worker tempworker){
        
        GridPane grid = new GridPane();
        grid.setStyle(styles.getSalesGridPane());
        grid.setPrefHeight(300);
        grid.setPrefWidth(500);
        grid.setHgap(20);
        grid.setVgap(20);

        workerFullName = new Label(tempworker.getFullName());
        workerFullName.setStyle(styles.getSalesLabel());
        TextField newName = new TextField();
        newName.setStyle(styles.getLoginTextFieldStyle());
        
        Label totalSales= new Label("Sales : "+tempworker.getTotalPurchases());
        totalSales.setStyle(styles.getSalesLabel());
        Label totalBuys= new Label("Bought : "+tempworker.getTotalBuys());
        totalBuys.setStyle(styles.getSalesLabel());
        workerEmail = new Label(tempworker.getEmail());
        workerEmail.setStyle(styles.getSalesLabel());
        TextField newEmail = new TextField();
        newEmail.setStyle(styles.getLoginTextFieldStyle());
                        
        ChoiceBox<Worker.ACCESSLEVEL> newAccessLevel = new ChoiceBox<>();
        newAccessLevel.getItems().addAll(Worker.ACCESSLEVEL.values());
        newAccessLevel.setStyle(styles.getSearchListStyle());
        newAccessLevel.setValue(tempworker.getACCESSLEVEL());
        
        CheckBox newPermitionToPurchaseCheckBox = new CheckBox("Permition to purchase");
        
        newPermitionToPurchaseCheckBox.setOnAction(e->{
            if(newPermitionToPurchaseCheckBox.isSelected()){
                permitionToPurchase = true;
            } else {
                permitionToPurchase = false;
            }
            System.out.println(permitionToPurchase);
        });
        
        CheckBox newPremitionToCheckLib = new CheckBox("Check librararian");

        newPremitionToCheckLib.setOnAction(e->{
            if(newPremitionToCheckLib.isSelected()){
                permitionToCheckLib = true;
            } else {
                permitionToCheckLib = false;
            }
            System.out.println(permitionToCheckLib);
        });

        CheckBox newPremitionToBill = new CheckBox("Permition to bill");

        newPremitionToBill.setOnAction(e->{
            if(newPremitionToBill.isSelected()){
                permitionToBill = true;
            } else {
                permitionToBill = false;
            }
            System.out.println(permitionToBill);
        });

        newAccessLevel.setOnKeyPressed(e->{
            if(KeyCode.ENTER == e.getCode()){
                if(newAccessLevel.getValue().equals(ACCESSLEVEL.MANAGER)){
                    grid.getChildren().remove(newPremitionToBill);
                    
                    grid.add(newPremitionToCheckLib, 0, 6);
                    grid.add(newPermitionToPurchaseCheckBox, 1, 6);
                    if(tempworker instanceof Manager){
                        newPremitionToCheckLib.setSelected(((Manager) tempworker).isCheckLibrarians());
                        newPermitionToPurchaseCheckBox.setSelected(((Manager) tempworker).isPermitionToPurchse());
                    }
                } 
                else if(newAccessLevel.getValue().equals(ACCESSLEVEL.LIBRARIAN)){
                    grid.getChildren().removeAll(newPremitionToCheckLib, newPermitionToPurchaseCheckBox);
                    grid.add(newPremitionToBill, 0, 6);
                   if (tempworker instanceof Librarian) newPremitionToBill.setSelected(((Librarian) tempworker).isPermitionToBill());
                    System.out.println(newPremitionToBill.isSelected());
                }
            }
            
        });


        workerSalary = new Label("" + tempworker.getSalary());
        workerSalary.setStyle(styles.getSalesLabel());
        TextField newSalary = new TextField();
        newSalary.setStyle(styles.getLoginTextFieldStyle());

        workerPhoneNumber = new Label(tempworker.getPhone());
        workerPhoneNumber.setStyle(styles.getSalesLabel());
        TextField newPhoneNumber = new TextField();
        newPhoneNumber.setStyle(styles.getLoginTextFieldStyle());

        

        Button deletWorkerBtn = new Button("Delete Worker");
        deletWorkerBtn.setStyle(styles.getLogOutBtnStyle());
        if(!(worker.getACCESSLEVEL().toString().equals("ADMIN"))){
            deletWorkerBtn.setDisable(true);
        }
        Button editWorkerBtn = new Button("Edit Worker");
        editWorkerBtn.setStyle(styles.getLogOutBtnStyle());
        if(!(worker.getACCESSLEVEL().toString().equals("ADMIN"))){
            editWorkerBtn.setDisable(true);
        }

        deletWorkerBtn.setOnAction(e -> {
            workers.deleteWorker(tempworker);
            EmployeeInfoHolder.getChildren().clear();
            primaryStage.setScene(new Scene(new MainPage(primaryStage, this.worker).getRoot(),800, 600));
            primaryStage.setFullScreen(true);
        });

        editWorkerBtn.setOnAction(e -> {
            workers.editWorker(tempworker, workerFullName.getText(), workerEmail.getText(), newAccessLevel.getValue(), Float.parseFloat(workerSalary.getText()), workerPhoneNumber.getText()
            ,permitionToCheckLib, permitionToPurchase, permitionToBill);
            EmployeeInfoHolder.getChildren().clear();
            primaryStage.setScene(new Scene(new MainPage(primaryStage, this.worker).getRoot(),800, 600));
            primaryStage.setFullScreen(true);
        });

        workerEmail.setOnMouseClicked(e -> {
            grid.getChildren().remove(workerEmail);
            grid.add(newEmail, 0, 1);

            newEmail.setOnKeyPressed(event -> {
                if(KeyCode.ENTER == event.getCode()){
                    workerEmail.setText(newEmail.getText());
                    workerEmail.setStyle(styles.getSalesLabel());
                    grid.getChildren().remove(newEmail);
                    grid.add(workerEmail, 0, 1);
                }
            });
        });

        workerSalary.setOnMouseClicked(e -> {
            grid.getChildren().remove(workerSalary);
            grid.add(newSalary, 0, 3);

            newSalary.setOnKeyPressed(event -> {
                if(KeyCode.ENTER == event.getCode()){
                    workerSalary.setText(newSalary.getText());
                    workerSalary.setStyle(styles.getSalesLabel());
                    grid.getChildren().remove(newSalary);
                    grid.add(workerSalary, 0, 3);
                }
            });
        });
        
        workerPhoneNumber.setOnMouseClicked(e -> {
            grid.getChildren().remove(workerPhoneNumber);
            grid.add(newPhoneNumber, 0, 4);

            newPhoneNumber.setOnKeyPressed(event -> {
                if(KeyCode.ENTER == event.getCode()){
                    workerPhoneNumber.setText(newPhoneNumber.getText());
                    workerPhoneNumber.setStyle(styles.getSalesLabel());
                    grid.getChildren().remove(newPhoneNumber);
                    grid.add(workerPhoneNumber, 0, 4);
                }
            });
        });

        grid.add(workerFullName, 0, 0);
        grid.add(workerEmail, 0, 1);
        grid.add(newAccessLevel, 0, 2);
        grid.add(workerSalary, 0, 3);
        grid.add(workerPhoneNumber, 0, 4);
        grid.add(totalSales, 0, 5);
        if(tempworker instanceof Manager||tempworker instanceof Admin)
        grid.add(totalBuys,0,7);
        grid.add(deletWorkerBtn, 0, 8);
        grid.add(editWorkerBtn, 1, 8);
        
        return grid;
    }
}