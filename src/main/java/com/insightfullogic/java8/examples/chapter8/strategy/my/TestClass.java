package com.insightfullogic.java8.examples.chapter8.strategy.my;

public class TestClass {
    private final OneInterface oneInterface;
    public TestClass(OneInterface oneInterface) {
        this.oneInterface = oneInterface;
    }

    public static void main(String[] args) {
        TestClass testClass1 = new TestClass(new Implement1());
        TestClass testClass2 = new TestClass(new Implement2());
        TestClass testClass3 = new TestClass(String::new);
    }
}
