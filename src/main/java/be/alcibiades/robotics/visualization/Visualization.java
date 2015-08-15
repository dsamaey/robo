package be.alcibiades.robotics.visualization;

import be.alcibiades.robotics.particlefiltermaze.Pose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Visualization {

    private BufferedImage image;
    private File imageFolder;
    private File videoFile;
    private ImageType imageType;

    public Visualization() {
        image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
    }

    public Visualization imageFolder(File imageFolder) {
        this.imageFolder = imageFolder;
        return this;
    }

    public Visualization videoFile(File videoFile) {
        this.videoFile = videoFile;
        return this;
    }

    public Visualization imageType(ImageType imageType) {
        this.imageType = imageType;
        return this;
    }

    public Visualization draw(Pose pose) {
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawLine((int) pose.getX(), (int)pose.getY(), (int)(pose.getX() + 10 * Math.cos(pose.getDirection())), (int)(pose.getY() + 10 * Math.sin(pose.getDirection())));
        return this;
    }

    public Visualization next() {
        image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        return this;
    }

    public File store(File directory, String fileName) throws IOException {
        File outputFile = new File(directory, fileName + '.' + imageType.getTypeName());
        ImageIO.write(image, imageType.getTypeName(), outputFile);
        return outputFile;
    }

    public BufferedImage getCurrentImage() {
        return image;
    }
}
