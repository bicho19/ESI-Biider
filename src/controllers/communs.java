package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import utils.Main;

/**
 * Created by Creator on 21/04/2016.
 */

public class communs {

    @FXML
    Button btnClose = new Button();
    @FXML ToolBar toolBar;
    @FXML Button btnRechercher = new Button() ;
    @FXML Button btnSupprimer = new Button() ;
    @FXML Button btnModifier = new Button() ;
    @FXML Button btnAjouter = new Button() ;
    @FXML Button btnMinim = new Button();
    @FXML JFXButton settings = new JFXButton();
    @FXML JFXButton about = new JFXButton();
    @FXML JFXButton mydb = new JFXButton();
    @FXML JFXButton help = new JFXButton();



    private double xOffset;
    private double yOffset;


    @FXML public void onSettingMoved(){
        settings.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onSettingLeave(){
        settings.setBackground(null);
    }
    @FXML public void onHelpMoved(){
        help.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onHelpLeave(){
        help.setBackground(null);
    }
    @FXML public void onAboutMoved(){
        about.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onAboutLeave(){
        about.setBackground(null);
    }
    @FXML public void onMydbMoved(){
        mydb.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onMydbLeave(){
        mydb.setBackground(null);
    }

    // These two methods helps to move the window from any place on the scene
    @FXML public void setOnMousePressed(MouseEvent event){
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    @FXML public void setOnMouseDragged(MouseEvent event){
        Stage stage = (Stage) toolBar.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset );
    }
    // These 2 method are obvious
    @FXML public void closeButtonAction(ActionEvent actionEvent) throws Exception{
        // get a handle to the stage
        // The Exception is for the overriding
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML public void minimizeButton(){
        Stage stage=(Stage) btnMinim.getScene().getWindow();
        stage.setIconified(true);
    }
    // These 4 methods to change between the scenes
    @FXML public void onAjouterAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/ajouter.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);

        stage = (Stage) btnAjouter.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        scene.getStylesheets().add(getClass().getResource("/ui/style/style_ajouter.css").toExternalForm());
        stage.setTitle("Ajouter");
        stage.setScene(scene);
        stage.show();

    }
    @FXML public void onModifierAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/modifier.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);

        stage = (Stage) btnModifier.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        scene.getStylesheets().add(getClass().getResource("/ui/style/style_modifier.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Modifier");
        stage.show();

    }
    @FXML public void onRechercherAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/rechercher.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);

        stage = (Stage) btnRechercher.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        scene.getStylesheets().add(getClass().getResource("/ui/style/style_rechercher.css").toExternalForm());
        stage.setTitle("Rechercher");
        stage.setScene(scene);
        stage.show();

    }
    @FXML public void onSupprimerAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/supprimer.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);

        stage = (Stage) btnSupprimer.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        scene.getStylesheets().add(getClass().getResource("/ui/style/style_supprimer.css").toExternalForm());
        stage.setTitle("Supprimer");
        stage.setScene(scene);
        stage.show();
    }
}
