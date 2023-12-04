package Staff;


public class Manager extends Worker {
    private boolean permitionToPurchse,checkLibrarians;
    public Manager(String fullName, String phone, String email, float salary,String dateOfBirth, Gender gender,String password,ACCESSLEVEL ACCESSLEVEL,
    boolean permitionToPurchse,boolean checkLibrarians ) {
        super(fullName, phone, email, dateOfBirth, salary,password,ACCESSLEVEL,gender);
        this.checkLibrarians=checkLibrarians;
        this.permitionToPurchse=permitionToPurchse;
    }

    @Override
    public void interact() {
        // TODO Auto-generated method stub    
    }
    public boolean isCheckLibrarians() {
        return checkLibrarians;
    }

    public void setCheckLibrarians(boolean checkLibrarians) {
        this.checkLibrarians = checkLibrarians;
    }

    public boolean isPermitionToPurchse() {
        return permitionToPurchse;
    }

    public void setPermitionToPurchse(boolean permitionToPurchse) {
        this.permitionToPurchse = permitionToPurchse;
    }

    
}
