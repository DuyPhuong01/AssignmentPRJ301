
package model;

/**
 *
 * @author Duy Phuong
 */
public class Product {
    private int productID;
    private String productName;
    private int brandID;
    private double price;
    private String color;
    private int quantity;
    private int soldQuantity;
    private String image;
    private int status;

    public Product() {
    }

    public Product(String productName, int brandID, double price, int quantity, String image, int status) {
        this.productName = productName;
        this.brandID = brandID;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.status = status;
    }

    public Product(int productID, String productName, int brandID, double price, int quantity, String image, int status) {
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.status = status;
    }

    public Product(int productID, String productName, int brandID, double price, int quantity, int soldQuantity, String image, int status) {
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.price = price;
        this.quantity = quantity;
        this.soldQuantity = soldQuantity;
        this.image = image;
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", brandID=" + brandID + ", price=" + price + ", color=" + color + ", quantity=" + quantity + ", soldQuantity=" + soldQuantity + ", image=" + image + ", status=" + status + '}';
    }

    
}
