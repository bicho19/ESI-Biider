package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Created by Creator on 11/05/2016.
 */
public class updateUser_controller extends communs {

    @FXML JFXButton btnUpdate = new JFXButton();
    @FXML ImageView success = new ImageView();
    @FXML JFXTextField previousPass = new JFXTextField();
    @FXML JFXTextField newPass = new JFXTextField();
    @FXML JFXTextField confirmPass = new JFXTextField();
    @FXML VBox vBox = new VBox();
    @FXML Label changed = new Label();


    public void onUpdateClick(){
        vBox.getChildren().remove(7);
        success.setVisible(true);
        changed.setVisible(true);
        previousPass.setText("");
        newPass.setText("");
        confirmPass.setText("");
    }
}


