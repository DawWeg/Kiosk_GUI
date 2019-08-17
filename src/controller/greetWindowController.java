package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class greetWindowController {

    @FXML
    Button proceedButton;

    @FXML
    private void proceedClicked() throws IOException {
        Stage mainWindow = new Stage();
        Stage stage = (Stage) proceedButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
        Parent root = loader.load();
        mainWindow.setScene(new Scene(root, 1280, 720));
        mainWindow.initStyle(StageStyle.UNDECORATED);
        stage.close();
        mainWindow.show();
    }
}
