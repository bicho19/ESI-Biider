package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Main;

/**
 * Created by Creator on 05/04/2016.
 */
public class ui_controller {
    @FXML Button btnUiClose = new Button();
    @FXML ToolBar toolBar;
    @FXML Button btnRechercher = new Button() ;
    @FXML Button btnSupprimer = new Button() ;
    @FXML Button btnModifier = new Button() ;
    @FXML Button btnAjouter = new Button() ;

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
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_ajouter.css").toExternalForm());
        btnAjouter.setOpacity(0.5);
        Stage stage = Main.getStage();
        stage.setTitle("Hrld");
        stage.setScene(scene);
        stage.show();
        Main.setStage(stage);
    }

    @FXML
    public void onModifierAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/modifier.fxml")));
        Scene scene = new Scene(Main.getRoot());
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_modifier.css").toExternalForm());

        Stage primaryStage;
        Stage stage = Main.getStage();
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();

        Main.setStage(stage);
        btnModifier.setOpacity(0.9);

    }
    @FXML
    public void onRechercherAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/rechercher.fxml")));
        Scene scene = new Scene(Main.getRoot());
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_rechercher.css").toExternalForm());

        Stage primaryStage;
        Stage stage = Main.getStage();
        stage.setScene(scene);
        stage.show();
        Main.setStage(stage);
        btnRechercher.setOpacity(0.9);

    }
    @FXML
    public void onSupprimerAction(ActionEvent actionEvent) throws Exception {
        Main.setRoot(FXMLLoader.load(getClass().getResource("/ui/supprimer.fxml")));
        Scene scene = new Scene(Main.getRoot());
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_supprimer.css").toExternalForm());

        Stage primaryStage;
        Stage stage = Main.getStage();
        stage.setScene(scene);
        stage.setTitle("Hello World");
        stage.show();

        Main.setStage(stage);
        btnSupprimer.setOpacity(0.9);

    }
}
