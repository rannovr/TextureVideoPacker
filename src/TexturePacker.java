import utils.VideoItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TexturePacker {
    public static int col, row, lcol, lrow, width, height;
    private static List<BufferedImage> all_images = new ArrayList<BufferedImage>();


    public static boolean isOstatok(int ost, ArrayList<Integer> mult) {
        if (ost == 0) return true;
        for (Integer i : mult) {
            if (i % ost == 0) {
                int colrow = getCallRows(i);

                lcol = Math.max(colrow, i / colrow);
                lrow = Math.min(colrow, i / colrow);

                return true;
            }
        }
        return false;
    }

    private static List<Integer> setColRows(List<Long> sizes, ArrayList<Integer> mult) {
        int max = getMaxSize(sizes, mult);
        int colrow = getCallRows(max);

        System.out.println("col=" + col + " row=" + row);
        System.out.println("lcol=" + lcol + " lrow=" + lrow);

        List<Integer> l = new ArrayList<Integer>();
        l.add(Math.max(colrow, max / colrow));
        l.add(Math.min(colrow, max / colrow));
        return l;
    }

    private static int getCallRows(Integer max) {
        int sqrt = (int) Math.sqrt(max);
        while (max % sqrt != 0) {
            sqrt++;
        }
        return sqrt;
    }

    public static ArrayList<Integer> getSizesMults() {
        ArrayList<Integer> mult = new ArrayList<Integer>();
        for (int i = 1; i <= col; i++) {
            for (int j = 1; j <= row; j++) {
                if (!mult.contains(i * j)) {
                    mult.add(i * j);
                }
            }
        }
        return mult;
    }

    private static Integer getMaxSize(List<Long> sizes, ArrayList<Integer> mult) {
        Integer max = Collections.max(mult);
        while (!isOstatok(sizes.size() % max, getSizesMults())) {
            mult.remove(max);
            max = Collections.max(mult);
        }

        System.out.println("max=" + max);
        return max;
    }

    public static int imageIterator = 0;

    public static BufferedImage createImage(int col, int row, int to) {
        BufferedImage bimg = new BufferedImage(width * col, height * row, BufferedImage.TYPE_USHORT_565_RGB);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bimg.createGraphics().drawImage(all_images.get(imageIterator++), j * width, i * height, null);
            }
        }
        return bimg;
    }

    public static ArrayList<Long> getSizes() throws IOException {
        ArrayList<Long> sizes = new ArrayList<Long>();
        File test = new File(TextureMaker.SOURCE_PATH + File.separator + "test.png");
        for (int i = 0; i < all_images.size(); i++) {
            BufferedImage bimg = all_images.get(i);
            ImageIO.write(bimg, "png", test);
            sizes.add(test.length());
        }
        test.delete();
        return sizes;
    }

    public static void reset(VideoItem vi){
        final File[] files = new File(vi.getFolderName() + File.separator +"textures").listFiles();
        for (File f: files) f.delete();
    }

    public static void pack(List<BufferedImage> images, VideoItem vi) throws IOException {
        reset(vi);

        width = vi.getWidth();
        height = vi.getHeight();

        col = 1024 / width;
        row = 1024 / height;

        lcol = 0;
        lrow = 0;

        all_images = images;

        List<Integer> cr = setColRows(getSizes(), getSizesMults());

        int tempCol = cr.get(0);
        int tempRow = cr.get(1);

        imageIterator = 0;
        int counter = 0;
        int to = images.size();

        while (imageIterator < to) {
            BufferedImage bimg;
            if (imageIterator == to - (lcol * lrow)) {
                tempCol = lcol;
                tempRow = lrow;
                bimg = createImage(lcol, lrow, 0);
            } else {
                bimg = createImage(tempCol, tempRow, to);
            }
            ImageIO.write(bimg, "png", new File(vi.getFolderName() + File.separator +"textures"+File.separator + counter++ + "-" + tempRow + "-" + tempCol + ".png"));
            System.out.println(counter - 1 + "-" + tempRow + "-" + tempCol);
        }
    }
}
