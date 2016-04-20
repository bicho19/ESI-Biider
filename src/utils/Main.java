package utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static Parent root;
    private static Stage stage;
    public static Parent getRoot() {
        return root;
    }
    public static void setRoot(Parent root) {
        Main.root = root;
    }
    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("/ui/login.fxml"));

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
