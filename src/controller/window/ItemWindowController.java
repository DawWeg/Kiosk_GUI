package controller.window;

import core.Item.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemWindowController implements Initializable{

    private Item item;
    private int count;
    private double sum;

    @FXML
    Button closeButton, leftArrowButton, rightArrowButton;

    @FXML
    Label sumLabel, countLabel;

    @FXML
    private void closeClicked(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void leftArrowClicked(){
        if(count <= 1) return;
        countLabel.setText(Integer.toString(--count));
        sumLabel.setText(Double.toString(item.getItemPrice()*count));
    }

    @FXML
    private void rightArrowClicked(){
        countLabel.setText(Integer.toString(++count));
        sumLabel.setText(Double.toString(item.getItemPrice()*count));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setup(Item item){

        this.item = item;
        count = 1;
        sum = item.getItemPrice()*count;
        countLabel.setText(Integer.toString(count));
        sumLabel.setText(Double.toString(sum));

        ImageView imageView0 = new ImageView(new Image("media/miscellaneous/closebutton.png"));
        imageView0.fitHeightProperty().setValue(40.0);
        imageView0.fitWidthProperty().setValue(40.0);
        closeButton.setGraphic(imageView0);
        closeButton.setMinSize(40, 40);
        closeButton.setMaxSize(40, 40);

        ImageView imageView1 = new ImageView(new Image("media/miscellaneous/leftArrow.png"));
        imageView1.fitHeightProperty().setValue(70.0);
        imageView1.fitWidthProperty().setValue(50);
        leftArrowButton.setGraphic(imageView1);
        leftArrowButton.setMinSize(45, 65);
        leftArrowButton.setMaxSize(45, 65);

        ImageView imageView2 = new ImageView(new Image("media/miscellaneous/rightArrow.png"));
        imageView2.fitHeightProperty().setValue(70.0);
        imageView2.fitWidthProperty().setValue(50);
        rightArrowButton.setGraphic(imageView2);
        rightArrowButton.setMinSize(45, 65);
        rightArrowButton.setMaxSize(45, 65);
    }
}
