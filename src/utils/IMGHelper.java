package utils;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ZeroOne on 4/19/2016.
 */
public class IMGHelper {


    /**
     * This method is for getting the user photo from the webcam;
     * to be completed later inshallah;
     */
    public void getUserPic(){
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        BufferedImage bufferedImage = webcam.getImage();
        try {
            ImageIO.write(bufferedImage,"JPG",new File("src/photos/test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webcam.close();
        }
    }
}
