package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/**
 * Created by Creator on 21/04/2016.
 */
public class settings_controller extends communs{
    @FXML TextField ID = new TextField();
    @FXML TextField Nom = new TextField();
    @FXML TextField Prenom = new TextField();
    @FXML TextField Date = new TextField();
    @FXML TextField Lieu = new TextField();
    @FXML TextField Adresse = new TextField();
    @FXML Button btnCapture = new Button();
    @FXML HBox hBox1 = new HBox();
    @FXML HBox hBox2 = new HBox();



    // On "Ajouter" button click
    @FXML private void btnAdd(){
        // Test the fields before add to the database
        if(ID.getText().equals("") || Nom.getText().equals("") || Prenom.getText().equals("") || Date.getText().equals("")
                || Lieu.getText().equals("") || Adresse.getText().equals("")){
            // Show error message if it didn't work
            System.out.println("You should give all the information");
        }else {
            // Make the query go ....
            System.out.println("... Like A Boss ...");
        }
    }
    @FXML public void btnCapture(ActionEvent actionEvent) throws Exception{
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
    @FXML public void onChangePasswordAction(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ui/layouts/popups/update_user.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/ui/style/jfoenix-components.css").toExternalForm());

            stage.setTitle("ABC");
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    @FXML public void onControlUsersAction(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ui/layouts/popups/users_list.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/ui/style/jfoenix-components.css").toExternalForm());
            stage.initStyle(StageStyle.DECORATED);

            stage.setTitle("ABC");
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    @FXML public void changePasswordMouseMoved(){
        hBox1.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.03), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void changePasswordMouseLeave(){
        hBox1.setBackground(null);
    }
    @FXML public void changeControlUsersMoved(){
        hBox2.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.03), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void changeControlUsersLeave(){
        hBox2.setBackground(null);
    }
}
