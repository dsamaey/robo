package be.alcibiades.robotics.particlefiltermaze;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WeightedSet<T> {

    private TreeSet<Weighted<T>> weightedObjects = new TreeSet<Weighted<T>>(new Comparator<Weighted<T>>() {
        public int compare(Weighted<T> o1, Weighted<T> o2) {
            int compare = -Double.compare(o1.getWeight(), o2.getWeight());
            if (compare == 0) {
                if (o1.getObject() instanceof Comparable) {
                    compare = ((Comparable) (o1.getObject())).compareTo(o2.getObject());
                }
            }
            if (compare == 0) {
                compare = Integer.compare(o1.getObject().hashCode(), o2.getObject().hashCode());
            }
            return compare;
        }
    });

    private double sum;

    public WeightedSet<T> add(T object, double weight) {
        return add(new Weighted<T>(weight, object));
    }

    public WeightedSet<T> add(Weighted<T> weightedObject) {
        if (weightedObject.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be strictly positive");
        }
        if (weightedObject.getObject() == null) {
            throw new IllegalArgumentException("Weighted objects can not be null");
        }
        weightedObjects.add(weightedObject);
        sum += weightedObject.getWeight();
        return this;
    }

    public Optional<T> sample() {
        if (weightedObjects.isEmpty()) {
            return Optional.empty();
        }
        double v = ThreadLocalRandom.current().nextDouble(sum);
        for (Weighted<T> weightedObject : weightedObjects) {
            v -= weightedObject.getWeight();
            if (v < 0) {
                return Optional.of(weightedObject.getObject());
            }
        }
        return Optional.of(weightedObjects.last().getObject());

    }
}
