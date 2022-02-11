package businessLayer;

import java.io.Serializable;

public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private Date orderDate;
    private int price;
    private static int idCounter=0;

    public Order(){}

    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getClientID() {
        return clientID;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setPrice(int price){ this.price=price; }
    public int getPrice() { return this.price; }

    public Order hashCode(int clientID, Date orderDate){
        Order order=new Order();
        this.orderID=++idCounter;
        this.clientID=clientID;
        this.orderDate=orderDate;
        return order;
    }
}
