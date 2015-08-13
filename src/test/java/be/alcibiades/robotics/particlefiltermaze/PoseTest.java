package be.alcibiades.robotics.particlefiltermaze;

import org.junit.Test;

import static org.junit.Assert.*;

public class PoseTest {

    @Test
    public void testMove() throws Exception {
        Pose move = new Pose(0, 0, 0).move(10, 0, 0);
        assertEquals(new Pose(10, 0, 0), move);
    }

    @Test
    public void testTurn() throws Exception {

    }
}