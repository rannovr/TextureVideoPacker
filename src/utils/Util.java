package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Util {

    public static Properties readINI(String filePath) {
        try {
            Properties pro = new Properties();
            pro.load(new FileInputStream(filePath));
            return pro;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getSubImage(BufferedImage image, VideoItem videoItem) {
        return image.getSubimage(videoItem.getX(), videoItem.getY(), videoItem.getWidth(), videoItem.getHeight());
    }

    public static List<BufferedImage> getBufferedImages(VideoItem videoItem) {
        List<BufferedImage> images = new ArrayList<BufferedImage>();
        File imageDir = new File(videoItem.getFolderName() + "/" + images);
        for (File imageFile : imageDir.listFiles()) {
            try {
                images.add(getSubImage(ImageIO.read(imageFile), videoItem));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images;
    }
}
