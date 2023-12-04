package Staff;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import BookstoreData.HeaderlessObjectOutputStream;
import Staff.Worker.ACCESSLEVEL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Style.*;

public class WorkerData implements Serializable {
    private static final long SerialVersionUID = 19184098341049090l;
    ArrayList <Worker>workerData;
    File file ;

    public WorkerData() {
     
     file= new File("workers.dat");
     workerData=new ArrayList<>();
     readWorkerData();
    }
    public void readWorkerData() {
        
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
			Worker worker;
			while(true) {
				worker = (Worker)reader.readObject();
                if (worker instanceof Librarian)
				workerData.add((Librarian)worker);
                if (worker instanceof Manager)
				workerData.add((Manager)worker);
                if (worker instanceof Admin)
				workerData.add((Admin)worker);
			}
		} catch (EOFException e) {
			System.out.println("Read all the books from the file");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		} catch(IOException e) {
			System.out.println("Error reading from file");
		}
        
    
    }
    public void deleteWorker(Worker worker) {
        workerData.remove(worker);
        rewirteFile();
    }
    public boolean rewirteFile() {
		try {
			
			FileOutputStream outputStream = new FileOutputStream(file);
			ObjectOutputStream 	writer = new ObjectOutputStream(outputStream); 
			for(Worker b : workerData) {
				writer.writeObject(b);
			}
			writer.close();
			return true;
		} catch(IOException ex) {
			return false;
		}
	}
    private boolean writeWorkerToFile(Worker newWorker) {
		try {
			
			FileOutputStream outputStream = new FileOutputStream(file, true);
			ObjectOutputStream writer;
			if (file.length() > 0)
				writer = new HeaderlessObjectOutputStream(outputStream);
			else
				writer = new ObjectOutputStream(outputStream); 
			writer.writeObject(newWorker);
			writer.close();
			return true;
		} catch(IOException ex) {
			return false;
		}
	}
    public boolean writeAllData(){
        File booksFile = new File("workers.dat");
			try {
                FileOutputStream outputStream = new FileOutputStream(booksFile);
                ObjectOutputStream writer= new ObjectOutputStream(outputStream); 
                for (Worker worker : workerData) {
                    writer.writeObject(worker);
                }
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
    }

    public void newWorkerForm(Stage primarystage, Worker workertemp) {
        Stage stage= new Stage();
        SettingStyles settingStyles= new SettingStyles();
        
        TextField nameText=new TextField();
        nameText.setPromptText("name");
        nameText.setStyle(settingStyles.getLoginTextFieldStyle());

        TextField emailText=new TextField();
        emailText.setPromptText("email");
        emailText.setStyle(settingStyles.getLoginTextFieldStyle());

        TextField phoneText=new TextField();
        phoneText.setPromptText("phone number");
        phoneText.setStyle(settingStyles.getLoginTextFieldStyle());
       
        TextField salaryText=new TextField();
        salaryText.setPromptText("salary");
        salaryText.setStyle(settingStyles.getLoginTextFieldStyle());

        //Text Field for Phone
        PasswordField passwordText=new PasswordField();
        passwordText.setPromptText("password");
        passwordText.setStyle(settingStyles.getLoginTextFieldStyle()); 


        //date picker to choose date
        DatePicker datePicker=new DatePicker();
        datePicker.setPromptText("Date of birth");
        datePicker.setStyle(settingStyles.getDatePicker());
        datePicker.setPrefWidth(270);

        //Toggle group of radio button
        ToggleGroup groupGender=new ToggleGroup();
        RadioButton maleRadio=new RadioButton("male");
        maleRadio.setStyle(settingStyles.getRadioBtn());
        maleRadio.setToggleGroup(groupGender);
        RadioButton femaleRadio=new RadioButton("female");
        femaleRadio.setStyle(settingStyles.getRadioBtn());
        femaleRadio.setToggleGroup(groupGender);
        HBox radioBox=new HBox(20);
        radioBox.getChildren().addAll(maleRadio,femaleRadio);

        //Check box for permissions
        CheckBox javaCheckBox=new CheckBox("Sell Books");
        javaCheckBox.setIndeterminate(false);
        CheckBox PurchaseBooksCheckBox=new CheckBox("Purchase Books");
        javaCheckBox.setIndeterminate(false);
        CheckBox checkLibrariansCheckBox=new CheckBox("CheckLibrarians");
        javaCheckBox.setIndeterminate(false);
    
        GridPane gridPane=new GridPane();
        //Choice box for location
        ChoiceBox locationChoiceBox=new ChoiceBox();
        locationChoiceBox.getItems().addAll("Librarian","Manager","Administrator");
        //locationChoiceBox.setValue("Librarian");
        locationChoiceBox.setStyle(settingStyles.getSearchListStyle());
        //setOnMouseEntered
        locationChoiceBox.setOnKeyPressed(e->{
            KeyCombination keyCombination=KeyCombination.valueOf("Enter");

            if(keyCombination.match(e)){
                if(locationChoiceBox.getValue().toString().equals("Librarian")){
                    gridPane.getChildren().removeAll(PurchaseBooksCheckBox,checkLibrariansCheckBox);
                    gridPane.add(javaCheckBox, 1, 8);
                }else if(locationChoiceBox.getValue().toString().equals("Manager")){
                    gridPane.getChildren().remove(javaCheckBox);
                    gridPane.add(PurchaseBooksCheckBox, 1, 8);
                    gridPane.add(checkLibrariansCheckBox, 1, 9);
                }else{
                    gridPane.getChildren().removeAll(javaCheckBox, PurchaseBooksCheckBox,checkLibrariansCheckBox);
                }
            }
        });

        //Label for register
        Button buttonRegister=new Button("Register");
        
       buttonRegister.setOnMouseClicked(event ->{
            String name=nameText.getText();
            String email=emailText.getText();
            String phone=phoneText.getText();
            float salary=Float.parseFloat(salaryText.getText());
            String password=passwordText.getText();
            String date= datePicker.getValue().toString();
            Gender gender;
            if(maleRadio.isSelected())gender=Gender.MALE;
            else gender=Gender.FEMALE;
            boolean librarian=locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Librarian");
            boolean manager=locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Manager");
            boolean admin=locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Administrator");
            boolean permitionToBill,permitionToPurchase,permitionToCheckLibrarians;
            permitionToBill=javaCheckBox.isSelected();
            permitionToPurchase=PurchaseBooksCheckBox.isSelected();
            permitionToCheckLibrarians=checkLibrariansCheckBox.isSelected();
           
          if (admin){
            Worker worker=new Admin(name,phone,email,salary,date,gender,password,ACCESSLEVEL.ADMIN);
            writeWorkerToFile(worker);
            workerData.add(worker);
            stage.close();
           
        }
        else if(manager){
            Worker worker=new Manager(name,phone,email,salary,date,gender,password,ACCESSLEVEL.MANAGER,permitionToPurchase,permitionToCheckLibrarians);
            writeWorkerToFile(worker);
            workerData.add(worker);
            stage.close();
        }
        else if(librarian){
            Worker worker=new Librarian(name,phone,email,date,gender,salary,password,ACCESSLEVEL.LIBRARIAN,permitionToBill);
            writeWorkerToFile(worker);
            workerData.add(worker);
            stage.close();
        }
        primarystage.setScene(new Scene(new MainPage(primarystage, workertemp).getRoot(),800, 600));
        primarystage.setFullScreen(true);
        });
        

        //Setting size for pane
        gridPane.setMinSize(500,600);

        //Setting the padding
        gridPane.setPadding(new Insets(30,30,30,50));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(25);
        gridPane.setHgap(10);

        //Setting the grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
       
        gridPane.add(nameText,1,0);
        gridPane.add(emailText,1,1);
        gridPane.add(phoneText,1,2);
        gridPane.add(datePicker,1,3);
        gridPane.add(salaryText,1,4);
        gridPane.add(passwordText,1,5);
        gridPane.add(radioBox,1,6);
        gridPane.add(locationChoiceBox,1,7);
        gridPane.add(buttonRegister,2,10);

        //Styling nodes
        buttonRegister.setStyle(settingStyles.getLogOutBtnStyle());
    

        //Setting the background color
        gridPane.setStyle(settingStyles.getLoginRootStyle());

        //Creating a scene object
        Scene scene=new Scene(gridPane,700,800);

       
        //Setting the title of stage
        stage.setTitle("Registration Form");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

 }
 public void editWorker(Worker worker,String name,String Email,ACCESSLEVEL accesslevel,float d, String phone,boolean permitionToCheckLibrarians ,boolean permitionToPurchase,boolean permitionToBill){
         if(accesslevel.equals(ACCESSLEVEL.LIBRARIAN)){
                Librarian librarian=new Librarian(name, phone, Email, worker.getDateOfBirth(), worker.getGender(), d, worker.getPassword(), ACCESSLEVEL.LIBRARIAN, permitionToBill);
                workerData.remove(worker);
                workerData.add(librarian);
                
            }
            else if(accesslevel.equals(ACCESSLEVEL.MANAGER)){
                Manager manager=new Manager(name, phone, Email, d, worker.getDateOfBirth(), worker.getGender(), worker.getPassword(), ACCESSLEVEL.MANAGER, permitionToPurchase, permitionToCheckLibrarians);
                workerData.remove(worker);
                workerData.add(manager);
            }else{
                Admin admin=new Admin(name, phone, Email, d, worker.getDateOfBirth(), worker.getGender(), worker.getPassword(), ACCESSLEVEL.ADMIN);
                workerData.remove(worker);
                workerData.add(admin);
            }

         rewirteFile();
 }
   
    public ArrayList<Worker> getData(){
        return workerData;
    }
    public ArrayList<Librarian> getLibrarians(){
        ArrayList<Librarian> librarians=new ArrayList<>();
        for (Worker worker:workerData){
            if (worker.getACCESSLEVEL().equals(ACCESSLEVEL.LIBRARIAN)){
                librarians.add((Librarian) worker);
            }
        }
        return librarians;
    }
    public Worker getWorkerFromEmail(String eamil){
         for (Worker worker:workerData){
             if (worker.getEmail().equals(eamil)){
                 return worker;
             }
         }
          return null;
    }
    public Worker getWorker(int index){
        return workerData.get(index);
    }
}

