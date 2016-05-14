package controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import entities.Usermaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
    @FXML JFXDatePicker updatebDate;

    private Usermaster usermaster;

    public void setUsermaster(Usermaster usermaster) {
        this.usermaster = usermaster;
    }


    public void onConfirmUpdate(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate localDate = java.sql.Date.valueOf(usermaster.getBirthDate()).toLocalDate();
        System.out.println();
        updateId.setText(usermaster.getId());
        updatefName.setText(usermaster.getFirstName());
        updatelName.setText(usermaster.getLastName());
        updatebDate.setValue(localDate);
        updatebPlace.setText(usermaster.getBirthPlace());
        updateAddress.setText(usermaster.getAddress());
        System.out.println(usermaster.getPhotos().toArray()[0]);

        }
}
