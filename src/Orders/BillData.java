package Orders;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BillData {
    ArrayList<BuyOrders> buys=new ArrayList<>();
    ArrayList<PurchaseOrders> purchases=new ArrayList<>();
    File purchasefile=new File("PurchaseBillData.dat");
    File buyfile=new File("BuysBillData.dat");
    public BillData (){
        readPurchaseBillsData();
        readBuyBillsData();
    }
    public void readPurchaseBillsData()  {
        
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(purchasefile))) {
			PurchaseOrders p;
            while (true){
                System.out.println("tits");
                p=(PurchaseOrders)reader.readObject();
                
                purchases.add(p);
            }
		} catch (EOFException e) {
			System.out.println("Read all the bills from the file");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		} catch(IOException e) {
			System.out.println("Error reading from bill file");
		}
    }

    public void readBuyBillsData()  {
        
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(buyfile))) {
			BuyOrders p;
            while (true){
                p=(BuyOrders)reader.readObject();
                buys.add(p);
            }
		} catch (EOFException e) {
			System.out.println("Read all the bills from the file");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		} catch(IOException e) {
			System.out.println("Error reading from bill file");
		}
    }
    public ArrayList<PurchaseOrders> getPurchasedBillsFromName(String name){
        ArrayList<PurchaseOrders> rez= new ArrayList<>();
        for (PurchaseOrders temp : purchases) {
            if (temp.getName().equals(name))rez.add(temp);
        }
        return rez;
    }
    public double getTotalSalesFromName(String name){
        double total=0;
        for (PurchaseOrders temp : getPurchasedBillsFromName(name)) {
            total+=temp.getTotalPrice();
        }
        return total;
    }
    public ArrayList<BuyOrders> getBuyBillsFromName(String name){
        ArrayList<BuyOrders> rez= new ArrayList<>();
        for (BuyOrders temp : buys) {
            if (temp.getName().equals(name))rez.add(temp);
        }
        return rez;
    }
    public double getTotalBuysFromName(String name){
        double total=0;
        for (BuyOrders temp : getBuyBillsFromName(name)) {
            total+=temp.getTotalPrice();
        }
        return total;
    }

    
}
