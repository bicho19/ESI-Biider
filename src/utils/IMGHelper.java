package utils;


import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_face;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;


public class IMGHelper {



    public void imgComparie(String trainingDir, String imgCmpDir){
        File root = new File(trainingDir);
        opencv_core.Mat cmpImage = imread(imgCmpDir, CV_LOAD_IMAGE_GRAYSCALE);

        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg")  || name.endsWith(".png");
            }
        };
        File[] imageFiles = root.listFiles(imgFilter);

        opencv_core.MatVector images = new opencv_core.MatVector(imageFiles.length);
        opencv_core.Mat labels = new opencv_core.Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.getIntBuffer();

        int counter = 0;

        for (File image : imageFiles) {
            opencv_core.Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);

            int label = Integer.parseInt(image.getName().split("\\-")[0]);

            images.put(counter, img);

            labelsBuf.put(counter, label);

            counter++;
        }
        int[] plabel = new int[1];
        double[] pconfidence = new double[1];

        opencv_face.FaceRecognizer faceRecognizer   = createLBPHFaceRecognizer();
        //opencv_face.FaceRecognizer faceRecognizer   = createFisherFaceRecognizer();
        //opencv_face.FaceRecognizer faceRecognizer     = createEigenFaceRecognizer();
        faceRecognizer.train(images, labels);
        faceRecognizer.predict(cmpImage,plabel,pconfidence);
        System.out.println("Predicted label: " + plabel[0] + " " + pconfidence[0]);

    }

    public void getImgcp(String trainingDir, String imgCmpDir){
        opencv_core.Mat testImage = imread(imgCmpDir, CV_LOAD_IMAGE_GRAYSCALE);
        File root = new File(trainingDir);
        FilenameFilter pngFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".jpg");
            }
        };
        File[] imageFiles = root.listFiles(pngFilter);

        MatVector images = new opencv_core.MatVector(imageFiles.length);
        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.getIntBuffer();

        int counter = 0;
        int label;

        IplImage img;

        Mat img2;
        IplImage grayImg=null;

        for (File image : imageFiles) {
            //img = cvLoadImage(image.getAbsolutePath(),CV_BGR2GRAY);
            img2 = imread(image.getAbsolutePath(),CV_LOAD_IMAGE_GRAYSCALE);
            int yer = image.getName().indexOf(".");
            String isim=image.getName().substring(0,yer);

            label = Integer.parseInt(isim);

            images.put(counter, img2);

            labelsBuf.put(counter, label);

            counter++;
        }


        FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
        //FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
        //FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();

        faceRecognizer.train(images, labels);

        int predictedLabel = faceRecognizer.predict(testImage);

        System.out.println("Predicted label: " + predictedLabel);
    }
}
