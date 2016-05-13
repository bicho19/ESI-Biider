package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Main;

public class communs {

    @FXML Button btnClose = new Button();
    @FXML ToolBar toolBar;
    @FXML JFXButton btnSettings = new JFXButton() ;
    @FXML JFXButton btnMinim = new JFXButton();
    @FXML JFXButton btnAbout = new JFXButton();
    @FXML JFXButton btnHelp = new JFXButton();
    @FXML JFXButton btnAdd = new JFXButton();
    @FXML JFXButton btnSearch = new JFXButton();
    @FXML JFXButton btnHome = new JFXButton();
    @FXML JFXButton btnLogOut = new JFXButton();

    private double xOffset;
    private double yOffset;

    /*
    * Fisrt method to change the style on mouse move
    * Second method to change the style on mouse leave
    * Third method to handle the click action (Change the scene)*/

    @FXML public void onLogOutMoved(Event event){
        btnLogOut.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onLogOutLeave(Event event){
        btnLogOut.setBackground(null);
    }
    @FXML public void onLogOutAction(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/layouts/login.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("ABC");
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) this.btnLogOut.getScene().getWindow();
        stage1.close();
    }


    @FXML public void onSettingMoved(Event event){
        btnSettings.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onSettingLeave(Event event){
        btnSettings.setBackground(null);
    }
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

    @FXML public void onAddMoved(Event event){
        btnAdd.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onAddLeave(Event event){
        btnAdd.setBackground(null);
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

    @FXML public void onHelpMoved(Event event){
        btnHelp.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onHelpLeave(Event event){
        btnHelp.setBackground(null);
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

    @FXML public void onAboutMoved(Event event){
        btnAbout.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onAboutLeave(Event event){
        btnAbout.setBackground(null);
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

    @FXML public void onHomeMoved(Event event) {
        btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML public void onHomeLeave(Event event) {
        btnHome.setBackground(null);
    }
    @FXML public void onHomeAction(ActionEvent actionEvent) throws Exception{
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/layouts/home.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);

        stage = (Stage) btnHome.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        btnHome.setStyle("-fx-font-weight: bold");
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();

    }

    @FXML public void onSearchMoved(Event event) {
        btnSearch.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.02), CornerRadii.EMPTY, Insets.EMPTY)));

    }
    @FXML public void onSearchLeave(Event event) {
        btnSearch.setBackground(null);

    }
    @FXML public void onSearchAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/layouts/search.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Stage stage = Main.getStage();
        Main.setStage(stage);

        stage = (Stage) btnSearch.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(true);
        }

        btnSearch.setStyle("-fx-font-weight: bold");
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        stage.setTitle("Search");
        stage.setScene(scene);
        stage.show();

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

}
