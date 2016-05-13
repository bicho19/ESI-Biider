package utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

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

    @Override public void start(Stage primaryStage) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/ui/layouts/search.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/ui/style/style_global.css").toExternalForm());
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

        stage = primaryStage;

    }

    public static void main(String[] args) { launch(args); }

}