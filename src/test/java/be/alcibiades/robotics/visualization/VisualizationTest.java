package be.alcibiades.robotics.visualization;

import be.alcibiades.robotics.particlefiltermaze.Pose;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.scale.AWTUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class VisualizationTest {

    @Test
    public void store() throws IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");
        File outputFile = new Visualization().draw(new Pose(10, 10, 45 * 2 * Math.PI / 360)).store(new File(tmpDir), "lol");
        System.out.println(outputFile);
    }

    @Test
    public void walk() throws IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");

        Visualization visualization = new Visualization().setVideoFile(new File(tmpDir, "walk.mp4"));
        Pose pose = new Pose(10, 10, 45 * 2 * Math.PI / 360);
        for (int i = 0; i < 40; ++i) {
            pose = pose.move(20, 0.5, 0.1);
            visualization.draw(pose);
            visualization.next();
        }
        visualization.finish();
    }

}