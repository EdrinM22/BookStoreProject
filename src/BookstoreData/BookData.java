package BookstoreData;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import BookstoreData.Book.Genre;
import Staff.Worker;
import Orders.*;
import Style.MainPage;
import Style.SettingStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookData implements Serializable {

    private static final long serialVersionUID = 1L;
    ArrayList<Book> books;
    File file ;

    public BookData() {
        books = new ArrayList<Book>();
        file = new File("BookDataFile.dat");
            readBookData();
        
    }

    public void readBookData() {
        
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
			Book book;
			while(true) {
				book = (Book)reader.readObject();
				books.add(book);
			}
		} catch (EOFException e) {
			System.out.println("Read all the books from the file");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		} catch(IOException e) {
			System.out.println("Error reading from file");
		}
        
        
    }
    public void newBookForm(Stage primaryStage, Worker temp) {
		SettingStyles settingStyles = new SettingStyles();
        Stage stage = new Stage();
        GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(50, 10, 50, 10));
		pane.setVgap(15);
		pane.setHgap(10);
		pane.setStyle(settingStyles.getLoginRootStyle());
		
		TextField titleTF = new TextField();
		titleTF.setStyle(settingStyles.getLoginTextFieldStyle());
		titleTF.setPromptText("Title");
		Label titleLbl = new Label("Title");
		
		TextField isbnTF = new TextField();
		isbnTF.setStyle(settingStyles.getLoginTextFieldStyle());
		isbnTF.setPromptText("ISBN 13");
		Label isbnLbl = new Label("ISBN 13");
		
		TextField priceTF = new TextField();
		priceTF.setStyle(settingStyles.getLoginTextFieldStyle());
		priceTF.setPromptText("Price");
		Label priceLbl = new Label("Price");
		
		Label versionLbl = new Label("Version");
		RadioButton rbPaperback = new RadioButton("Paperback");
		rbPaperback.setStyle(settingStyles.getRadioBtn());
		RadioButton rbEbook = new RadioButton("E-book");
		rbEbook.setStyle(settingStyles.getRadioBtn());
		ToggleGroup group = new ToggleGroup();
		rbPaperback.setToggleGroup(group);
		rbEbook.setToggleGroup(group);
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(rbPaperback, rbEbook);
		
		Label descriptionLbl = new Label("Description");
		TextArea descriptionTA = new TextArea();
		descriptionTA.setStyle("-fx-background-color: #0E273C; -fx-text-fill: #0E273C; -fx-font-size: 14px; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-border-color: #35CE8D; -fx-border-width: 3px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");
		descriptionTA.setPrefColumnCount(20);
		descriptionTA.setPrefRowCount(5);
		descriptionTA.setWrapText(true);
		
		
		Label authosLbl = new Label("Select an author: ");
		TextField authorTF = new TextField();
		authorTF.setStyle(settingStyles.getLoginTextFieldStyle());
		authorTF.setPromptText("Author");
		// genres
		VBox paneForGenres = new VBox(10);
		paneForGenres.setPadding(new Insets(4));
		ArrayList<CheckBox> genreCheckboxes = new ArrayList<>();
		for(Genre g : Genre.values()) {
			genreCheckboxes.add(new CheckBox(g.toString()));
		}
		paneForGenres.getChildren().addAll(genreCheckboxes);
		Label genreLbl = new Label("Genres: ");
		
		Button submitBtn = new Button("Submit");
		submitBtn.setStyle(settingStyles.getLogOutBtnStyle());

		submitBtn.setOnAction(e -> {
			if(titleTF.getText().isEmpty() || isbnTF.getText().isEmpty() || priceTF.getText().isEmpty() || descriptionTA.getText().isEmpty() || authorTF.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error");
				alert.setContentText("Please fill all the fields");
				alert.showAndWait();
			}
			else if(!checkIsbn13( isbnTF.getText())){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error");
				alert.setContentText("ISBN 13 is not valid");
				alert.showAndWait();
			}
			else {
				String isbn13 = isbnTF.getText();
				String title = titleTF.getText();
				float price = Float.parseFloat(priceTF.getText());
				String description = descriptionTA.getText();
				String author = authorTF.getText();
				boolean isPaperback = rbPaperback.isSelected();
				Book newBook = new Book(isbn13, title, description, price, author, isPaperback,0);
				for(int i=0; i < genreCheckboxes.size(); i++) {
					if(genreCheckboxes.get(i).isSelected())
						newBook.addGenre(Genre.values()[i]);
						
				}
				boolean res = writeBookToFile(newBook);
				books.add(newBook);
				primaryStage.setScene(new Scene(new MainPage(primaryStage, temp).getRoot()));
        		primaryStage.setFullScreen(true);
				// primaryStage.close();
				stage.close();
			}
			// String isbn13 = isbnTF.getText();
			// String title = titleTF.getText();
			// float price = Float.parseFloat(priceTF.getText());
			// String description = descriptionTA.getText();
			// String author = authorTF.getText();
			// boolean isPaperback = rbPaperback.isSelected();
			// Book newBook = new Book(isbn13, title, description, price, author, isPaperback,0);
			// for(int i=0; i < genreCheckboxes.size(); i++) {
			// 	if(genreCheckboxes.get(i).isSelected())
			// 		newBook.addGenre(Genre.values()[i]);
					
			// }
		});
		
	
		pane.add(titleTF, 1, 0);
		pane.add(isbnTF, 1, 1);
		pane.add(priceTF, 1, 2);
		pane.add(hbox, 1, 3);
		pane.add(descriptionTA, 1, 4);
		pane.add(authorTF, 1, 5);
		pane.add(paneForGenres, 1, 6);
		pane.add(submitBtn, 1, 7);
		
		Scene scene = new Scene(pane, 700, 700);
		stage.setScene(scene);
		stage.setTitle("Bookstore");
		stage.show();
    }
	private boolean checkIsbn13(String isbn13) {
		if(isbn13.matches("[0-9]{13}"))
			return true;
		return false;
	}
    private boolean writeBookToFile(Book newBook) {
		try {
			
			FileOutputStream outputStream = new FileOutputStream(file, true);
			ObjectOutputStream writer;
			if (file.length() > 0)
				writer = new HeaderlessObjectOutputStream(outputStream);
			else
				writer = new ObjectOutputStream(outputStream); 
			writer.writeObject(newBook);
			writer.close();
			return true;
		} catch(IOException ex) {
			return false;
		}
	}
	private boolean rewirteFile() {
		try {
			
			FileOutputStream outputStream = new FileOutputStream(file);
			ObjectOutputStream 	writer = new ObjectOutputStream(outputStream); 
			for(Book b : books) {
				writer.writeObject(b);
			}
			writer.close();
			return true;
		} catch(IOException ex) {
			return false;
		}
	}

    public void addBooksToStock(BuyOrders buyOreder) {
         for (int index = 0; index < buyOreder.getIsbns().size(); index++) {
			Book book = getBook(buyOreder.getIsbns().get(index));
			book.addStock((int)buyOreder.getQuantity().get(index));
		 }
		 rewirteFile();
		
	   }
	public void removeBooksFromStock(PurchaseOrders sellOrder) {
		for (int index = 0; index < sellOrder.getIsbns().size(); index++) {
			Book book = getBook(sellOrder.getIsbns().get(index));
			book.removeStock(sellOrder.getQuantity().get(index));
		}
		rewirteFile();
	}
	public Book getBook(String isbn) {
		for (Book book : books) {
			if (book.getIsbn13().equals(isbn))
				return book;
		}
		return null;
	}


    public ArrayList<Book> getFromName(String name){
        ArrayList<Book> temp=new ArrayList<>();    
        for (Book book : books) {
                if (book.getTitle().contains(name))temp.add(book);
            }
            return temp;
    }

	public Book searchByTitle(String title){
		for( Book b: books){
			if(b.getTitle().equals(title)){
				return b;
			}
		}
		return null;
	}
	
    public ArrayList<Book> getBooks(){
            return this.books;
    }

	public int getBookQuantity(String isbn) {
		return getBook(isbn).getStockInt();
	}
}

