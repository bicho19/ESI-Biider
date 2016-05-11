package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

/**c
 * Created by Creator on 11/05/2016.
 */
public class add_controller extends communs{
    @FXML TextField ID = new TextField();
    @FXML TextField Nom = new TextField();
    @FXML TextField Prenom = new TextField();
    @FXML TextField Date = new TextField();
    @FXML TextField Lieu = new TextField();
    @FXML TextField Adresse = new TextField();
    @FXML Button btnCapture = new Button();

    // On "Ajouter" button click
    @FXML
    private void btnAdd(){
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
}
