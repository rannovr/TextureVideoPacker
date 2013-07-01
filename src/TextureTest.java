import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: rnovruzov
 * Date: 01.07.13
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class TextureTest {
    public static String PATH = "c:\\Users\\rnovruzov\\workspace\\TextureFromVideo\\videos\\";

    public static void main(String args[]) throws IOException, JCodecException {
        for (int i=0; i< 100; i++){
            BufferedImage frame = FrameGrab.getFrame(new File(PATH + "bored.mp4"), i);
            ImageIO.write(frame, "png", new File(PATH + "frame_"+i+".png"));
        }
    }

}
