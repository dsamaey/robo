package be.alcibiades.robotics.particlefiltermaze;

import java.util.Random;

public class Pose {
    private static final Random RANDOM = new Random();

    private double x;
    private double y;
    private double direction;

    public Pose(double x, double y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDirection() {
        return direction;
    }

    public Pose move(double distance, double distanceSpread, double directionSpread) {
        distance += distanceSpread * RANDOM.nextGaussian();
        return new Pose(x + distance * Math.cos(direction), y + distance * Math.sin(direction), direction + directionSpread * RANDOM.nextGaussian());
    }

    public Pose turn(double angle) {
        return new Pose(x, y, direction + angle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pose pose = (Pose) o;

        if (Double.compare(pose.x, x) != 0) return false;
        if (Double.compare(pose.y, y) != 0) return false;
        return Double.compare(pose.direction, direction) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(direction);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
