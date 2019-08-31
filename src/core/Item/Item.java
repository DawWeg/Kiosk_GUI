package core.Item;

import javafx.scene.image.Image;

public class Item {

    private String itemName;
    private Image itemImage;

    public Item(){
        itemName = "";
    }

    public Item(String itemName, String imageURL){
        this.itemName = itemName;
        itemImage = new Image(imageURL);
    }

    public String getItemName(){
        return itemName;
    }

    public Image getItemImage() {
        return itemImage;
    }
}
