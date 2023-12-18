package Orders;

import java.io.Serial;
import java.io.Serializable;

public class Buy implements Serializable {
    @Serial
    private static final long serialVersionUID = 529482940413L;
    private final String name;
    private final double totalPrice;
    private final long time;
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
