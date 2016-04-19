package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.DBHelper;
import utils.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Creator on 05/04/2016.
 */
public class ui_controller implements Initializable{
    @FXML Button btnUiClose = new Button();
    @FXML private ToolBar toolBar;
    private double xOffset;
    private double yOffset;


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
        Stage stage = (Stage) btnUiClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    public void onAjouterAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/ajouter.fxml")));
        Scene scene = new Scene(Main.getRoot());
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_ui.css").toExternalForm());

        Stage primaryStage;
        Stage stage = Main.getStage();

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();

        Main.setStage(stage);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBHelper dbHelper = new DBHelper();
        System.out.println(dbHelper.getUserById("ES2201141435013206"));
    }
}
