
package model;

/**
 *
 * @author Duy Phuong
 */
public class Category {
    private int categoryID;
    private String categoryName, description;
    private boolean activate;

    public Category() {
    }

    public Category(int categoryID, String categoryName, String description, boolean activate) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
        this.activate = activate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

   
}
