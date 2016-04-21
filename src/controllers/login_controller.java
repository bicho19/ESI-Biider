package controllers;

import entities.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import utils.DBHelper;

public class login_controller extends communs{
    @FXML private Button btnClose;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private DBHelper dbHelper;

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    // Handle the login button and make the connection to the database
    public void handleLogin(ActionEvent actionEvent) throws Exception {
        dbHelper = new DBHelper();
        String user_name = usernameField.getText();
        String pass_word = passwordField.getText();
        Admin admin = new Admin(user_name,pass_word);
        Admin test = dbHelper.checkLoginAdmin(admin);
        if (/*(user_name.equals("admin")) && (pass_word.equals("admin"))*/true){
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
}
