package Staff;

import java.io.Serial;
import java.io.Serializable;

public abstract class Worker implements Serializable {
    @Serial
    private static final long serialVersionUID = 529670540410483L;
    private String fullName;
    private String phone;
    private String email;
    private String dateOfBirth;
    private String password;
    private float salary;
    private String Status;
    private ACCESSLEVEL AccesLevel;
    private Gender gender;
    private double totalPurchases;
    private double totalBuys;
    public Worker(String fullName, String phone, String email, String dateOfBirth,
    float salary,String password,ACCESSLEVEL ACCESSLEVEL1,Gender gender) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.password=password;
        this.dateOfBirth = dateOfBirth;
        this.AccesLevel = ACCESSLEVEL1;
        setStatus(ACCESSLEVEL1);
        this.gender=gender;
    }

    public abstract void interact();

    public double getTotalPurchases() {
        return totalPurchases;
    }
    
    public void addPurchases(double totalPurchases) {
        this.totalPurchases += totalPurchases;
        
    }
    public double getTotalBuys() {
        return totalBuys;
    }
    public void addBuys(double totalPurchases) {
        this.totalBuys += totalPurchases;
    }
    public String getFullName() {
        return fullName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(ACCESSLEVEL a) {
       if(a.equals(ACCESSLEVEL.LIBRARIAN))this.Status="Librarian";
       else if(a.equals(ACCESSLEVEL.MANAGER))this.Status="Manager";
       else if(a.equals(ACCESSLEVEL.ADMIN))this.Status="Administator";
       else this.Status="Null";
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public float getSalary() {
        return salary;
    }

    public Gender getGender() {
        return gender;
    }
    

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ACCESSLEVEL getACCESSLEVEL() {
        return  AccesLevel;
    }

    public void setACCESSLEVEL(ACCESSLEVEL ACCESSLEVEL1) {
        this.AccesLevel = ACCESSLEVEL1;
    }
    public enum ACCESSLEVEL{
        ADMIN,MANAGER,LIBRARIAN
    }
}
