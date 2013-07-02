import utils.Util;
import utils.VideoItem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TextureMaker {
    public static String SOURCE_PATH = "e:\\3\\";
    public static String RESULT_PATH = "e:\\0\\";
    public static List<VideoItem> videoItems = new ArrayList<VideoItem>();

    public static void main(String args[]) throws IOException {
        init();
        for(VideoItem videoItem : videoItems){
            TexturePacker.pack(getBufferedImages(videoItem), videoItem);
        }
    }

    public static void init() {
        File folder = new File(SOURCE_PATH);
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isDirectory()) {
                Properties ini = Util.readINI(listOfFile.getPath() + File.separator + "init.ini");
                VideoItem vi = new VideoItem(   Integer.parseInt(ini.getProperty("x")),
                                                Integer.parseInt(ini.getProperty("y")),
                                                Integer.parseInt(ini.getProperty("width")),
                                                Integer.parseInt(ini.getProperty("height")),
                                                listOfFile.getPath() + File.separator + ini.getProperty("videoName"),
                                                listOfFile.getPath());
                videoItems.add(vi);
            }
        }
    }

}
