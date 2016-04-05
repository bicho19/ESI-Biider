package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by Creator on 05/04/2016.
 */
public class ui_controller {
    @FXML Button btnUiClose = new Button();

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnUiClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
