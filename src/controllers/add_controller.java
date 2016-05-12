package controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**c
 * Created by Creator on 11/05/2016.
 */
public class add_controller extends communs implements Initializable{
    @FXML JFXTextField ID = new JFXTextField();
    @FXML JFXTextField fName = new JFXTextField();
    @FXML JFXTextField lName = new JFXTextField();
    @FXML JFXDatePicker Date = new JFXDatePicker();
    @FXML JFXTextField bPlace = new JFXTextField();
    @FXML JFXTextField Address = new JFXTextField();
    @FXML JFXComboBox<String> Sex = new JFXComboBox<>();
    @FXML JFXButton btnCapture = new JFXButton();
    @FXML JFXButton btnLoad = new JFXButton();
    @FXML JFXButton btnAdd = new JFXButton();

    // On "Ajouter" button click
    @FXML
    public void onAddClick(){
        LocalDate localDate = Date.getValue();
        String sex = Sex.getSelectionModel().getSelectedItem();

        if (ID.getText().equals("") || fName.getText().equals("") || lName.getText().equals("") ||
                Date.getValue() == null || bPlace.getText().equals("") || Address.getText().equals("") || Sex.getSelectionModel().getSelectedItem().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please fill all the information");
                    alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);        }

        // Test the fields before add to the database

    }
    @FXML public void onCaptureClick(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/layouts/popups/webcam.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_settings.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle("ABC");
        stage.setScene(scene);
        stage.show();

    }
    @FXML public void btnParcourir(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Dialog");
        File file = fileChooser.showOpenDialog(new Stage());
        String path = file.getPath();
        System.out.println(path);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll("Male","Female");
        Sex.setItems(options);
    }



}
