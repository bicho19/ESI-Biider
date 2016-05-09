package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.StageStyle;
import utils.DBHelper;

import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;

public class login_controller extends communs {

    @FXML
    private ToolBar toolBar;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private JFXButton btnLogin = new JFXButton();
    private double xOffset;
    private double yOffset;
    private DBHelper dbHelper;

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
        Admin admin = new Admin(user_name, pass_word);
        Admin test = dbHelper.checkLoginAdmin(admin);
        if (true) {
            System.out.println("It's good");
            Parent root = FXMLLoader.load(getClass().getResource("/ui/rechercher.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/ui/style/jfoenix-components.css").toExternalForm());
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setTitle("ABC");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) this.btnLogin.getScene().getWindow();
            stage1.close();
        } else {
            System.out.println("not good");
        }
    }
}
