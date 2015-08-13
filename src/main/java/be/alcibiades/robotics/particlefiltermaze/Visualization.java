package be.alcibiades.robotics.particlefiltermaze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Visualization {

    private BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);

    public Visualization draw(Pose pose) {
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawLine((int) pose.getX(), (int)pose.getY(), (int)(pose.getX() + 10 * Math.cos(pose.getDirection())), (int)(pose.getY() + 10 * Math.sin(pose.getDirection())));
        return this;
    }

    public File store(File directory, String fileName) throws IOException {
        File outputFile = new File(directory, fileName + ".png");
        ImageIO.write(image, "png", outputFile);
        return outputFile;
    }

    public BufferedImage getImage() {
        return image;
    }
}
