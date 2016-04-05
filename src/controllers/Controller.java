package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private javafx.scene.control.Button btnClose;
    @FXML private javafx.scene.control.ToolBar toolBar;
    private double xOffset;
    private double yOffset;


    @FXML private void setOnMousePressed(MouseEvent event){
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML private void setOnMouseDragged(MouseEvent event){
        Stage stage = (Stage) toolBar.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset );
    }
    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
