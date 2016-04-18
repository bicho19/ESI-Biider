package controllers;

import entities.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import utils.DBHelper;

import java.sql.Connection;

public class login_controller {
    @FXML private Button btnClose;
    @FXML private ToolBar toolBar;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
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

    public void handleLogin(ActionEvent actionEvent) {
        dbHelper = new DBHelper();
        String user_name = usernameField.getText();
        String pass_word = passwordField.getText();
        Admin admin = new Admin(user_name,pass_word);
        Admin test = dbHelper.checkLoginAdmin(admin);
        if ((user_name.equals("admin")) && (pass_word.equals("admin"))){
            System.out.println("It's good");
        }else {
            System.out.println("not good");
        }
    }
}
