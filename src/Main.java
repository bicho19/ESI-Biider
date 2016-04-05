import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/sample.fxml"));
        BorderPane borderPane = new BorderPane();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("ui/style/style_login.css").toExternalForm());
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
