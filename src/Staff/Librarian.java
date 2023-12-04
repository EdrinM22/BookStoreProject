package Staff;

public class Librarian extends Worker {
    private boolean permitionToBill;
    
    
    public Librarian(String fullName, String phone, String email, String dateOfBirth,Gender gender,float salary, String password,ACCESSLEVEL ACCESSLEVEL,boolean permitionToBill) {
        super(fullName, phone, email, dateOfBirth, salary,password,ACCESSLEVEL,gender);
        this.permitionToBill = permitionToBill;
    }

    public boolean isPermitionToBill() {
        return permitionToBill;
    }

    public void setPermitionToBill(boolean permitionToBill) {
        this.permitionToBill = permitionToBill;
    }

    @Override
    public void interact() {
        // TODO Auto-generated method stub
        
    }
    
}
