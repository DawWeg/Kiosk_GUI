package controller.window;

import core.Item.ItemType;
import core.MyImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    private final MyImage SETS = new MyImage("/media/types/sets.png", "Sets");
    private final MyImage DRINKS = new MyImage("/media/types/drinks.png", "Drinks");
    private final MyImage SANDWICHES = new MyImage("/media/types/sandwiches.png", "Sandwiches");
    private final MyImage DESSERTS = new MyImage("/media/types/desserts.png", "Desserts");
    //private final MyImage[] listOfImages = {SETS, DRINKS, SANDWICHES, DESSERTS};

    ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private BorderPane centerPane;

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ItemType[] itemTypes = setItemTypes();
        setListView(itemTypes);
    }

    private ItemType[] setItemTypes(){
        ItemType sets = new ItemType("Sets", "/media/types/sets.png");
        ItemType drinks = new ItemType("Drinks", "/media/types/drinks.png");
        ItemType sandwiches = new ItemType("Sandwiches", "/media/types/sandwiches.png");
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
    private void listViewClicked() throws IOException{
        if(!listView.getSelectionModel().isEmpty()){
            StringBuilder builder = new StringBuilder();
            builder.setLength(0);
            builder.append("../../fxml/scene/").append(listView.getSelectionModel().getSelectedItem()).append("Scene.fxml");
            BorderPane systemPane = FXMLLoader.load(getClass().getResource(builder.toString()));
            centerPane.getChildren().setAll(systemPane);
            systemPane.prefWidthProperty().bind(centerPane.widthProperty());
            systemPane.prefHeightProperty().bind(centerPane.heightProperty());
        }
    }
}
