package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Account;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utils.DBHelper;
import utils.Sessions;
import java.net.URL;
import java.util.ResourceBundle;
import static utils.Utils.*;

public class login_controller extends communs implements Initializable{

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private JFXButton btnLogin = new JFXButton();
    @FXML private Label errorMessage = new Label();
    private DBHelper dbHelper;
    int nTrys = 5;
    int seconds = 4;
    Timeline timeline;
    Sessions sessions;


    public void decrement(){
        errorMessage.setText("You have to wait "+seconds+" seconds");
        seconds--;
        if (seconds<0){
            timeline.stop();
            usernameField.setDisable(false);
            passwordField.setDisable(false);
            btnLogin.setDisable(false);
            errorMessage.setText("");
            seconds=4;nTrys = 5;
        }

    }

    public void handleLogin(ActionEvent actionEvent) throws Exception {
        sessions = new Sessions();
        String user_name = usernameField.getText();
        String pass_word = passHash(passwordField.getText());
        Account account = new Account(user_name, pass_word);
        if (true/*dbHelper.checkLoginAccount(account)*/) {
            Account account1 = dbHelper.getAccountByUsername(user_name);
            sessions.setPrefs(user_name,true,account1.getAccType());
            Parent root = FXMLLoader.load(getClass().getResource("/ui/layouts/home.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setTitle("ABC");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) this.btnLogin.getScene().getWindow();
            stage1.close();
        } else {
            if(nTrys!=0) {
                errorMessage.setText("Wrong password .. "+nTrys+" tries left.");
                errorMessage.setStyle("-fx-font-weight: bold;-fx-text-fill: #ff484b");
                nTrys--;
            }else{
                usernameField.setDisable(true);
                passwordField.setDisable(true);
                btnLogin.setDisable(true);
                errorMessage.setText("You have to wait "+(seconds+1)+" seconds");

                timeline = new Timeline(new KeyFrame(
                    Duration.millis(1000), e->decrement()));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();


            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbHelper = new DBHelper();
    }
}
