package controller.window;

import core.Item.Item;
import core.Item.ItemType;
import core.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    ItemType[] itemTypes;

    ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private GridPane centerPane;

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        itemTypes = setItemTypes();
        setListView(itemTypes);
    }

    private ItemType[] setItemTypes(){
        ItemType sets = new ItemType("Sets", "/media/types/sets.png");

        ItemType drinks = new ItemType("Drinks", "/media/types/drinks.png");
        drinks.addItem("Coffee", "/media/drinks/coffee.png")
              .addItem("Cola", "/media/drinks/cola.png")
              .addItem("Juice", "/media/drinks/juice.png")
              .addItem("Shake", "/media/drinks/shake.png")
              .addItem("Water", "/media/drinks/water.png");

        ItemType sandwiches = new ItemType("Sandwiches", "/media/types/sandwiches.png");
        sandwiches.addItem("Big Mac", "/media/sandwiches/bigmac.png")
                  .addItem("Mc Royal", "/media/sandwiches/mcroyal.png")
                  .addItem("Mc Chicken", "/media/sandwiches/mcchicken.png")
                  .addItem("Mc Double", "/media/sandwiches/mcdouble.png")
                  .addItem("Wieśmac", "/media/sandwiches/wiesmac.png");

        ItemType desserts = new ItemType("Desserts", "/media/types/desserts.png");

        ItemType[] itemTypes = {sets, drinks, sandwiches, desserts};
        return itemTypes;
    }

    private void setListView(ItemType[] itemTypes){
        items.removeAll(items);
        for(ItemType itemType : itemTypes) items.add(itemType.getItemTypeName());

        listView.getItems().addAll(items);

        listView.setCellFactory(param -> new ListCell<>(){
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                imageView.fitWidthProperty().setValue(100);
                imageView.fitHeightProperty().setValue(100);

                super.updateItem(name, empty);
                if(empty){
                    setText(null);
                    setGraphic(null);
                    setStyle("-fx-background-color: #D3D3D3");
                } else {
                    setStyle("-fx-background-color: #D3D3D3");
                    for(int i = 0; i < itemTypes.length; i++){
                        for(int j = 0; j < itemTypes.length; j++){
                            if(name.equals(itemTypes[j].getItemTypeName())){
                                imageView.setImage(itemTypes[j].getItemTypeImage());
                                setText(itemTypes[j].getItemTypeName());
                                setGraphic(imageView);
                            }
                        }
                    }
                }
            }
        });
    }

    @FXML
    private void listViewClicked() {
/*        if(!listView.getSelectionModel().isEmpty()){
            StringBuilder builder = new StringBuilder();
            builder.setLength(0);
            builder.append("../../fxml/scene/").append(listView.getSelectionModel().getSelectedItem()).append("Scene.fxml");
            GridPane centerPane = FXMLLoader.load(getClass().getResource(builder.toString()));
            centerPane.getChildren().setAll(centerPane);
            centerPane.prefWidthProperty().bind(centerPane.widthProperty());
            centerPane.prefHeightProperty().bind(centerPane.heightProperty());
        }*/
        if(!listView.getSelectionModel().isEmpty()){
            centerPane.getChildren().removeAll(centerPane.getChildren());
            for(ItemType itemType : itemTypes){
                if(listView.getSelectionModel().getSelectedItem() == itemType.getItemTypeName()){
                    int i = 0;
                    int j = 0;
                    for(Item item : itemType.getItemList()){
                        ImageView imageView = new ImageView(item.getItemImage());
                        imageView.fitWidthProperty().setValue(100);
                        imageView.fitHeightProperty().setValue(100);
                        centerPane.add(imageView, i, j);
                        if((++i % 4) == 3){
                            i = 0;
                            j++;
                        }
                    }
                }
            }

        }
    }

    @FXML
    private void gridPaneClicked(){
        //System.out.println()
    }
}
