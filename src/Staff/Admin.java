package Staff;

public class Admin extends Worker {

    public Admin(String fullName, String phone, String email, float salary,String dateOfBirth, Gender gender,String password,ACCESSLEVEL ACCESSLEVEL) {
        super(fullName, phone, email, dateOfBirth, salary,password,ACCESSLEVEL,gender);
    }

    @Override
    public void interact() {
        // TODO Auto-generated method stub
    }
    
}
