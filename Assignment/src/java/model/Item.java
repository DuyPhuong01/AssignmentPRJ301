
package model;

/**
 *
 * @author Duy Phuong
 */
public class Item {
    private Product product;
    private int orderID;
    private int quantity;

    public Item() {
    }

    public Item(Product product, int orderID, int quantity) {
        this.product = product;
        this.orderID = orderID;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Item{" + "product=" + product + ", quantity=" + quantity + '}';
    }
}
