package com.insightfullogic.java8.answers.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class MapUsingReduceGhz {

    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
            List<O> newList = new ArrayList<O>(acc);
            newList.add(mapper.apply(x));
            return newList;
        }, (List<O> left, List<O> right) -> {
            List<O> newLeft = new ArrayList<O>(left);
            newLeft.addAll(right);
            return newLeft;
        });
    }
}
