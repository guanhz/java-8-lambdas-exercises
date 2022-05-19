package com.insightfullogic.java8.examples.chapter8.strategy.my;

public class Implement2 implements OneInterface{
    @Override
    public String aMethod(StringBuffer buffer) {
        return "2: " + buffer.toString();
    }
}
