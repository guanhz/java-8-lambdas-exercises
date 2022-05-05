package com.insightfullogic.java8.answers.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterUsingReduceGhz {

    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {
        return stream.reduce(new ArrayList<I>(), (List<I> acc, I x) -> {
            if (predicate.test(x)) {
                List<I> newAcc = new ArrayList<>(acc);
                newAcc.add(x);
                return newAcc;
            } else {
                return acc;
            }
        }, FilterUsingReduceGhz::combineLists);
    }

    private static <I> List<I> combineLists(List<I> left, List<I> right) {
        List<I> newLeft = new ArrayList<I>(left);
        newLeft.addAll(right);
        return newLeft;
    }
}
