package core.Item;

import javafx.scene.image.Image;

public class Item {

    private String itemName;
    private double itemPrice;
    private Image itemImage;

    public Item(){
        itemName = "";
        itemPrice = 0.0;
    }

    public Item(String itemName, double itemPrice, String imageURL){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        itemImage = new Image(imageURL);

    }

    public String getItemName(){
        return itemName;
    }

    public Image getItemImage() {
        return itemImage;
    }

    public double getItemPrice() { return itemPrice; }
}
