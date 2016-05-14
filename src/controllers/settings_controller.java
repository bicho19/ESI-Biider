package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class settings_controller extends communs{

    @FXML HBox hBox1 = new HBox();
    @FXML HBox hBox2 = new HBox();

    @FXML public void onChangePasswordAction(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ui/layouts/popups/update_user.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/ui/style/style_add.css").toExternalForm());

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
            scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
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
