package com.insightfullogic.java8.answers.chapter3;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class MapUsingReduceGhzTest {

    @Test
    public void identityMapsToItself() {
        assertMapped((Integer x) -> x, asList(1, 2, 3), asList(1, 2, 3));
    }

    @Test
    public void incrementingNumbers() {
        assertMapped((Integer x) -> x + 2, asList(1, 2, 3), asList(3, 4, 5));
    }

    private <I, O> void assertMapped(Function<I, O> mapper, List<I> inputList, List<O> expectedOutputList) {
        List<O> outputList = MapUsingReduceGhz.map(inputList.stream(), mapper);
        assertEquals(outputList, expectedOutputList);
    }
}
