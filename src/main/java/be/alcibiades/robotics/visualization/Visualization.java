package be.alcibiades.robotics.visualization;

import be.alcibiades.robotics.particlefiltermaze.Pose;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Visualization {

    private BufferedImage image;
    private File imageFolder;
    private File videoFile;
    private ImageType imageType = ImageType.PNG;
    private int width = 1000;
    private int height = 1000;
    private SequenceEncoder sequenceEncoder;
    private State state = State.CONFIGURING;

    public Visualization() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public Visualization setImageFolder(File imageFolder) {
        this.imageFolder = imageFolder;
        return this;
    }

    public Visualization setVideoFile(File videoFile) throws IOException {
        this.videoFile = videoFile;
        return this;
    }

    public Visualization setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Visualization setImageType(ImageType imageType) {
        this.imageType = imageType;
        return this;
    }

    public Visualization draw(Pose pose) {
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawLine((int) pose.getX(), (int) pose.getY(), (int) (pose.getX() + 10 * Math.cos(pose.getDirection())), (int) (pose.getY() + 10 * Math.sin(pose.getDirection())));
        return this;
    }

    public Visualization next() {
        try {
            if (sequenceEncoder == null) {
                sequenceEncoder = new SequenceEncoder(videoFile);
            }
            sequenceEncoder.encodeNativeFrame(AWTUtil.fromBufferedImage(image));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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

    public void finish() {
        requireState();
        if (sequenceEncoder != null) {
            try {
                sequenceEncoder.finish();
            } catch (IOException e) {
                throw new IllegalStateException();
            }
        }

    }

    private void requireState() {
        
    }

    private enum State {
        CONFIGURING, DRAWING, FINISHED
    }
}
