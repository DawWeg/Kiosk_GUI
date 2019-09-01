package controller.window;

import core.Item.Item;
import core.Item.ItemType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    ItemType[] itemTypes;

    ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane centerPane;

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        itemTypes = setItemTypes();
        setListView(itemTypes);
    }

    private ItemType[] setItemTypes(){
        ItemType sets = new ItemType("Sets", "/media/types/sets.png");
        sets.addItem("2 for U", 10.0, "/media/sets/2foru.png")
            .addItem("Happy Meal",  11.0, "/media/sets/happymeal.png")
            .addItem("Mc Set", 12.0, "/media/sets/mczestaw.png");

        ItemType drinks = new ItemType("Drinks", "/media/types/drinks.png");
        drinks.addItem("Kawa", 1.0, "/media/drinks/coffee.png")
              .addItem("Cola", 2.0, "/media/drinks/cola.png")
              .addItem("Sok", 3.0, "/media/drinks/juice.png")
              .addItem("Shake", 4.0, "/media/drinks/shake.png")
              .addItem("Woda", 5.0, "/media/drinks/water.png");

        ItemType sandwiches = new ItemType("Sandwiches", "/media/types/sandwiches.png");
        sandwiches.addItem("Big Mac", 6.0, "/media/sandwiches/bigmac.png")
                  .addItem("Mc Royal", 7.0, "/media/sandwiches/mcroyal.png")
                  .addItem("Mc Chicken", 8.0, "/media/sandwiches/mcchicken.png")
                  .addItem("Mc Double", 9.0, "/media/sandwiches/mcdouble.png")
                  .addItem("Mc Double", 10.0, "/media/sandwiches/mcdouble.png")
                  .addItem("Mc Double", 11.0, "/media/sandwiches/mcdouble.png")
                  .addItem("Mc Double", 12.0, "/media/sandwiches/mcdouble.png")
                  .addItem("Mc Double", 13.0, "/media/sandwiches/mcdouble.png")
                  .addItem("Mc Double", 14.0, "/media/sandwiches/mcdouble.png")
                  .addItem("Mc Double", 15.0, "/media/sandwiches/mcdouble.png")
                  .addItem("Mc Double", 16.0, "/media/sandwiches/mcdouble.png")
                  .addItem("Wieśmac", 17.0, "/media/sandwiches/wiesmac.png");

        ItemType desserts = new ItemType("Desserts", "/media/types/desserts.png");
        desserts.addItem("Ciastko", 18.0, "/media/desserts/ciastko.png")
                .addItem("Jabłka i winogrona", 19.0, "/media/desserts/jablka.png")
                .addItem("Marchewki", 20.0, "/media/desserts/marchewki.png")
                .addItem("Mc Flurry", 21.0, "/media/desserts/mcflurry.png");

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

        if(!listView.getSelectionModel().isEmpty()){
            centerPane.getChildren().removeAll(centerPane.getChildren());
            for(ItemType itemType : itemTypes){
                if(listView.getSelectionModel().getSelectedItem() == itemType.getItemTypeName()){
                    int i = 0;
                    int j = 0;
                    for(Item item : itemType.getItemList()){
                        double parent_width = centerPane.getWidth();
                        VBox vbox = new VBox();
                        vbox.setMinWidth(parent_width/3);
                        vbox.setPrefWidth(parent_width/3);
                        vbox.setMinHeight(vbox.getPrefHeight());
                        ImageView imageView = new ImageView(item.getItemImage());
                        imageView.fitWidthProperty().setValue(200);
                        imageView.fitHeightProperty().setValue(200);
                        Label label = new Label(item.getItemName());
                        vbox.getChildren().addAll(imageView, label);
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setOnMouseClicked(mouseEvent -> {
                            try {
                                gridPaneClicked(item);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        centerPane.add(vbox, i, j);
                        if((++i % 4) == 3){
                            i = 0;
                            j++;
                        }
                    }
                }
            }

        }
    }

    private void gridPaneClicked(Item item) throws IOException {
        Stage itemWindow = new Stage();
        itemWindow.initModality(Modality.APPLICATION_MODAL);
        itemWindow.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/window/itemWindow.fxml"));
        Parent root = loader.load();
        ItemWindowController itemWindowController = loader.getController();
        itemWindow.setScene(new Scene(root, 350, 250));
        itemWindowController.setup(item);
        itemWindow.showAndWait();

    }
}
