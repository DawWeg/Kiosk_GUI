package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class mainWindowController implements Initializable{

    private final Image BIG_MAC = new Image("/media/big_mac.png");
    private final Image MCROYAL = new Image("/media/mcroyal.png");
    private Image[] listOfImages = {BIG_MAC, MCROYAL};

    ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListView();
    }

    private void setListView(){
        items.removeAll(items);
        items.addAll("Big Mac", "Mc Royal");


        int n = listOfImages.length;

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
                    for(int i = 0; i < n; i++){
                        if(name.equals("Big Mac"))
                            imageView.setImage(listOfImages[0]);
                        else if(name.equals("Mc Royal"))
                            imageView.setImage(listOfImages[1]);
                        setText(name);
                        setGraphic(imageView);
                    }
                }
            }
        });
    }
}
