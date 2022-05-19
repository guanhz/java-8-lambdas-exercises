package com.insightfullogic.java8.examples.chapter8.strategy.my;

public class Implement1 implements OneInterface{
    @Override
    public String aMethod(StringBuffer buffer) {
        return "1: " + buffer.toString();
    }
}
