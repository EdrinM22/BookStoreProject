package Orders;

public class Buy {
    private static final long serialVersionUID = 529482940413L;
    private String name;
    private double totalPrice;
    private long time;
    Buy(String name,double totalPrice,long time){
        this.name=name;
        this.totalPrice=totalPrice;
        this.time=time;
    }

    public String getName() {
        return name;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public long getTime() {
        return time;
    }
}
