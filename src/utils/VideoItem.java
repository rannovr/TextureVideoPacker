package utils;

/**
 * Created by IntelliJ IDEA.
 * User: rnovruzov
 * Date: 1/18/13
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class VideoItem {
    private int x, y, width, height;
    private String videoName, folderName;

    public VideoItem(int x, int y, int width, int height, String videoName, String folderName) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.videoName = videoName;
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
