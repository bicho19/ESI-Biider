package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import utils.Main;

/**
 * Created by Creator on 21/04/2016.
 */

public class communs {

    @FXML Button btnClose = new Button();
    @FXML ToolBar toolBar;
    @FXML JFXButton btnSettings = new JFXButton() ;
    @FXML JFXButton btnMinim = new JFXButton();
    @FXML JFXButton btnAbout = new JFXButton();
    @FXML JFXButton btnMydb = new JFXButton();
    @FXML JFXButton btnHelp = new JFXButton();
    @FXML JFXButton btnAdd = new JFXButton();




    private double xOffset;
    private double yOffset;


    @FXML public void onSettingMoved(){
        btnSettings.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onSettingLeave(){
        btnSettings.setBackground(null);
    }
    @FXML public void onAddMoved(){
        btnAdd.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onAddLeave(){
        btnAdd.setBackground(null);
    }
    @FXML public void onHelpMoved(){
        btnHelp.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onHelpLeave(){
        btnHelp.setBackground(null);
    }
    @FXML public void onAboutMoved(){
        btnAbout.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onAboutLeave(){
        btnAbout.setBackground(null);
    }
    @FXML public void onMydbMoved(){
        btnMydb.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onMydbLeave(){
        btnMydb.setBackground(null);
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
    @FXML public void closeButtonAction(){
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
    @FXML public void onSettingsAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/layouts/settings.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);

        stage = (Stage) btnSettings.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        btnSettings.setStyle("-fx-font-weight: bold");
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();

    }
    @FXML public void onHelpAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/layouts/help.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);
        btnHelp.setStyle("-fx-font-weight: bold");


        stage = (Stage) btnHelp.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }


        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Help");
        stage.show();

    }
    @FXML public void onMydbAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/layouts/mydb.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);

        btnMydb.setStyle("-fx-font-weight: bold");
        stage = (Stage) btnMydb.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        stage.setTitle("Mydb");
        stage.setScene(scene);
        stage.show();

    }
    @FXML public void onAboutAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/layouts/about.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);


        stage = (Stage) btnAbout.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }
    @FXML public void onAddAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/layouts/add.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);


        stage = (Stage) btnAdd.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        stage.setTitle("Add");
        stage.setScene(scene);
        stage.show();
    }

    public void onHomeMoved(Event event) {

    }

    public void onHomeLeave(Event event) {

    }

    public void onHomeAction(ActionEvent actionEvent) {

    }

    public void onSearchMoved(Event event) {

    }

    public void onSearchLeave(Event event) {

    }

    public void onSearchAction(ActionEvent actionEvent) {

    }
}
