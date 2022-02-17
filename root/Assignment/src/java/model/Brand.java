
package model;

/**
 *
 * @author Duy Phuong
 */
public class Brand {
    private int brandID;
    private String brandName;
    private String brandLogo;

    public Brand() {
    }

    public Brand(int brandID, String brandName, String brandLogo) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandLogo = brandLogo;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }
}
