
package model;

/**
 *
 * @author Duy Phuong
 */
public class Color {
    private String colorID;
    private String colorName;

    public Color() {
    }

    public Color(String colorID, String colorName) {
        this.colorID = colorID;
        this.colorName = colorName;
    }

    public String getColorID() {
        return colorID;
    }

    public void setColorID(String colorID) {
        this.colorID = colorID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return "Color{" + "colorID=" + colorID + "\ncolorName=" + colorName + '}';
    }  
    
}
