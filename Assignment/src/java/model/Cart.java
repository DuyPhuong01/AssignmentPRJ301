
package model;


import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Duy Phuong
 */
public class Cart {
    private List<Item> list;
    private Timestamp orderDate;
    private int totalPrice;
    private boolean status;
    private int paymentID;
    
    public Cart() {
    }

    public Cart(List<Item> list_items, boolean status) {
        this.list = list_items;
        this.status = status;
    }

    public Cart(List<Item> list, Timestamp orderDate, int totalPrice, boolean status) {
        this.list = list;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }
    
    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
}
