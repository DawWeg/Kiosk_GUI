package core.Item;

import javafx.scene.image.Image;

import java.util.List;

public class ItemType {

    private String itemTypeName;
    private Image itemTypeImage;
    private List<Item> items;

    public ItemType() {
        itemTypeName = "Unnamed";
        itemTypeImage = new Image("");
    }

    public ItemType(String itemTypeName, String imageURL) {
        this.itemTypeName = itemTypeName;
        itemTypeImage = new Image(imageURL);
    }

    public Image getItemTypeImage() {
        return itemTypeImage;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }
    
}



