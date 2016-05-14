package controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Creator on 15/05/2016.
 */
public class image_list_controller extends communs implements Initializable {
    public FlowPane flowPane = new FlowPane();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i=0;i<5;i++){
            ImageView imageView = new ImageView();
            imageView.setImage(new Image("/ui/images/add.png",50,50,true,true));
            flowPane.getChildren().add(imageView);

        }
    }
}
