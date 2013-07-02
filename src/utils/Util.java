package utils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
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

}
