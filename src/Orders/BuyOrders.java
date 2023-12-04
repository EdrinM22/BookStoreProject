package Orders;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


import BookstoreData.HeaderlessObjectOutputStream;

public class BuyOrders implements Serializable {
    private static final long serialVersionUID = 529482940413L;
    transient private ArrayList<String >isbn13;
    transient private ArrayList<Integer>quantity;
    private double totalPrice;
    private transient File file = new File("BuyBills.txt");
    private transient File filedata= new File("BuysBillData.dat");
    private String name;
    private long time;
    public BuyOrders(ArrayList<String>isbn13,ArrayList<Integer>quantity,double totalPrice,String name){
        this.isbn13=isbn13;
        this.quantity=quantity;
        this.totalPrice = totalPrice;
        this.name=name;
       if (time==0) this.time=System.currentTimeMillis();
        writeToFile();
        addToDatabase();
    }
    public double getTotalPrice(){
        return this.totalPrice;
    }
    public String getName(){
        return this.name;
    }
    private long getTime(){
        return this.time;
    }
        
    public ArrayList<String> getIsbns(){
        return isbn13;
    }
    public ArrayList<Integer> getQuantity(){
        return quantity;
    }

    private boolean addToDatabase() {
		try {
			
			FileOutputStream outputStream = new FileOutputStream(filedata,true);
			ObjectOutputStream writer;
			if (file.length() > 0)
				writer = new HeaderlessObjectOutputStream(outputStream);
			else
				writer = new ObjectOutputStream(outputStream); 
			writer.writeObject(new Buy(name, totalPrice, time));
			writer.close();
			return true;
		} catch(IOException ex) {
			return false;
		}
	}

    public void writeToFile() {
        
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("BuyBill");
            Date temp=new Date(time);
            writer.println(name+"    : "+temp.toString());
            for (int index = 0; index < isbn13.size(); index++) {
                writer.println("ISBN-> " + isbn13.get(index) + "\n\tQuantity " + quantity.get(index) + "\n");

            }

            writer.println("---------------------------");
            Integer total = 0;
            for ( Integer i : quantity) {
                total += i;
            }
            writer.println("\t\tTotal Books: " + total + " \n\t\tTotal Price: " + totalPrice + " \t\t");
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Bills File not found");
        }

        
    }
}
