package utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Main.root = root;
    }

    private static Parent root;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    private static Stage stage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("/ui/login.fxml"));
        BorderPane borderPane = new BorderPane();


        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_login.css").toExternalForm());
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

        stage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
