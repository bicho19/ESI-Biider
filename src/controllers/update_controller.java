package controllers;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import entities.Usermaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Creator on 14/05/2016.
 */
public class update_controller extends communs implements Initializable {
    @FXML JFXTextField updateId;
    @FXML JFXTextField updatefName;
    @FXML JFXTextField updatelName;
    @FXML JFXTextField updatebPlace;
    @FXML JFXTextField updateAddress;
    @FXML JFXTextField updatebDate;

    private Usermaster usermaster;

    public void setUsermaster(Usermaster usermaster) {
        this.usermaster = usermaster;
    }


    public void onConfirmUpdate(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateId.setText(usermaster.getId());
        updatefName.setText(usermaster.getFirstName());
        updatelName.setText(usermaster.getLastName());
        updatebDate.setText(usermaster.getBirthDate());
        updatebPlace.setText(usermaster.getBirthPlace());
        updateAddress.setText(usermaster.getAddress());
    }
}
