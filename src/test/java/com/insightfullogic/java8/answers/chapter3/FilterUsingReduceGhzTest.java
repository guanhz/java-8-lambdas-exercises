package com.insightfullogic.java8.answers.chapter3;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class FilterUsingReduceGhzTest {

    @Test
    public void filterUsingReduceGhzTest() {
        List<String> list = asList("a234", "b123", "c343");
        assertEquals(asList("b123", "c343"), FilterUsingReduceGhz.filter(list.stream(), (String s) -> !s.startsWith("a")));
    }
}
