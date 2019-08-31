package core.Item;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ItemType {

    private String itemTypeName;
    private Image itemTypeImage;
    private List<Item> items = new ArrayList<>();

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

    public ItemType addItem(String itemName, String imageURL){
        Item item = new Item(itemName, imageURL);
        items.add(item);
        return this;
    }

    public List<Item> getItemList(){
        return items;
    }
}



