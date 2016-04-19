package controllers;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.buildin.natives.Device;
import com.github.sarxos.webcam.ds.buildin.natives.DeviceList;
import com.github.sarxos.webcam.ds.buildin.natives.OpenIMAJGrabber;
import entities.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.StageStyle;
import org.bridj.Pointer;
import utils.DBHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class login_controller implements Initializable{
    @FXML private Button btnClose;
    @FXML private ToolBar toolBar;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button btnLogin = new Button();
    private double xOffset;
    private double yOffset;
    private DBHelper dbHelper;

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



    public void handleLogin(ActionEvent actionEvent) throws Exception {
        dbHelper = new DBHelper();
        String user_name = usernameField.getText();
        String pass_word = passwordField.getText();
        Admin admin = new Admin(user_name,pass_word);
        Admin test = dbHelper.checkLoginAdmin(admin);
        if ((user_name.equals("admin")) && (pass_word.equals("admin"))){
            System.out.println("It's good");
            Parent  root = FXMLLoader.load(getClass().getResource("/ui/rechercher.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/ui/style/style_rechercher.css").toExternalForm());
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setTitle("ABC");
            stage.setScene(scene);
            stage.show();
            closeButtonAction();

        }else {
            System.out.println("not good");
        }
    }

    /**
     * This method is called automatically after the java load the fxml file
     * so we put our code that needs to be launched auto here;
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
