
package model;

import java.util.List;

/**
 *
 * @author Duy Phuong
 */
public class Cart {
    private List<Item> list_items;
    private boolean status;
    
    public Cart() {
    }

    public Cart(List<Item> list_items, boolean status) {
        this.list_items = list_items;
        this.status = status;
    }

    public List<Item> getList_items() {
        return list_items;
    }

    public void setList_items(List<Item> list_items) {
        this.list_items = list_items;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
