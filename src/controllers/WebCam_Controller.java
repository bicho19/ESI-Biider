package controllers;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.WebCamInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class WebCam_Controller extends communs implements Initializable {
    @FXML BorderPane camRootPane;
    @FXML ImageView imgWebCamCapturedImage;
    @FXML BorderPane webCamPane;
    @FXML ComboBox<WebCamInfo> cameraOptions = new ComboBox<>();
    @FXML FlowPane bottomCameraControlPane;
    @FXML Button btnStartCamera;
    @FXML Button btnStopCamera;
    @FXML Button btnTakePhoto;
    @FXML Button btnDisposeCamera;
    private Webcam webCam = null;
    private boolean stopCamera = false;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    private String photoName;

    /**
     * This method will be used to pass photo name (user id + something) when opening this scene;
     * @param name
     */
    public void setPhotoName(String name){this.photoName = name;}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createCameraOptions();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setImageViewSize();
            }
        });

    }

    /**
     * This method is for setting the ImageView to match WebCam BorderPane
     * height and width;
     */
    private void setImageViewSize() {
        double height = webCamPane.getHeight();
        double width = webCamPane.getWidth();

        imgWebCamCapturedImage.setFitHeight(height);
        imgWebCamCapturedImage.setFitWidth(width);
        imgWebCamCapturedImage.prefHeight(height);
        imgWebCamCapturedImage.prefWidth(width);
        imgWebCamCapturedImage.setPreserveRatio(true);
    }

    /**
     * This method is for getting the WebCams available in the computer
     * and show them in the ComboBox (drop down) menu so the user can select the best match;
     */
    private void createCameraOptions() {
        int webCamCounter = 0;
        ObservableList<WebCamInfo> options = FXCollections.observableArrayList();
        for (Webcam webcam : Webcam.getWebcams()) {
            WebCamInfo webCamInfo = new WebCamInfo();
            webCamInfo.setWebCamIndex(webCamCounter);
            webCamInfo.setWebCamName(webcam.getName());
            options.add(webCamInfo);
            webCamCounter++;
        }

        cameraOptions.setItems(options);
        cameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {
            @Override
            public void changed(ObservableValue<? extends WebCamInfo> observable, WebCamInfo arg1, WebCamInfo arg2) {
                if(arg2 != null){
                    System.out.println("WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());
                    initializeWebCam(arg2.getWebCamIndex());
                }
            }
        });
        disableAllButton();
    }

    /**
     * This method is for initializing the WebCam on another Thread
     * We use Task and Thread here, You need to learn ore btnAbout them in near FUTURE;
     * Then it calls the startWebCamStream() to start the streaming in the ImageView;
     * @param webCamIndex
     */
    protected void initializeWebCam(final int webCamIndex){
        Task<Void> webCamTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                if (webCam != null) {
                    stopWebCamCamera();
                }

                webCam = Webcam.getWebcams().get(webCamIndex);
                webCam.open();

                startWebCamStream();

                return null;
            }
        };
        Thread webCamThread = new Thread(webCamTask);
        webCamThread.setDaemon(true);
        webCamThread.start();

        bottomCameraControlPane.setDisable(false);
        btnStartCamera.setDisable(true);
    }

    @FXML
    private void stopWebCamCamera() {
        stopCamera = true;
        btnStartCamera.setDisable(false);
        btnTakePhoto.setDisable(false);
        btnStopCamera.setDisable(true);

    }

    /**
     * This method start streaming from the WebCam to the ImageView right away;
     * it also Task and thread which run on another thread cheer up, our app run is MULTI THREADING ;)
     */
    @FXML
    protected void startWebCamStream(){
        stopCamera = false;

        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                final AtomicReference<WritableImage> ref = new AtomicReference<>();
                BufferedImage img = null;

                while (!stopCamera) {
                    try {
                        if ((img = webCam.getImage()) != null) {

                            ref.set(SwingFXUtils.toFXImage(img, ref.get()));
                            img.flush();

                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    imageProperty.set(ref.get());
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        imgWebCamCapturedImage.imageProperty().bind(imageProperty);
        enableAllButton();
    }

    public void savePhotoWebCam() {
        BufferedImage bufferedImage = webCam.getImage();
        try {
            ImageIO.write(bufferedImage,"JPG",new File("src/photos/test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disposeWebCamCamera();
        }
    }

    public void disposeWebCamCamera() {
        stopCamera = true;
        webCam.close();
        btnStartCamera.setDisable(true);
        btnStopCamera.setDisable(true);
        btnTakePhoto.setDisable(true);
    }
    private void disableAllButton(){
        btnTakePhoto.setDisable(true);
        btnStartCamera.setDisable(true);
        btnStopCamera.setDisable(true);
        btnDisposeCamera.setDisable(true);

    }

    private void enableAllButton(){
        btnTakePhoto.setDisable(true);
        btnStartCamera.setDisable(true);
        btnStopCamera.setDisable(false);
        btnDisposeCamera.setDisable(false);

    }
}
