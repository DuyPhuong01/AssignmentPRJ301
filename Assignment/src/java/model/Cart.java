
package model;

import java.util.List;

/**
 *
 * @author Duy Phuong
 */
public class Cart {
    private List<Item> list;
    private boolean status;
    
    public Cart() {
    }

    public Cart(List<Item> list_items, boolean status) {
        this.list = list_items;
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
}
