package be.alcibiades.robotics.particlefiltermaze;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class WeightedSetTest {

    @Test
    public void addOneObject() {
        Optional<Integer> sample = new WeightedSet<Integer>().add(1, 1).sample();
        assertTrue(sample.isPresent());
        assertEquals(1, sample.get().intValue());
    }

    @Test
    public void addTwoObjects() {
        WeightedSet<Integer> weightedSet = new WeightedSet<Integer>().add(1, 1).add(2, 3);
        Map<Integer, List<Integer>> collect = IntStream.rangeClosed(1, 10000).mapToObj(i -> weightedSet.sample().get()).collect(Collectors.groupingBy(o -> o));
        assertTrue(collect.containsKey(1));
        assertTrue(collect.containsKey(2));
        assertEquals(10000, collect.get(1).size() + collect.get(2).size());
    }
}