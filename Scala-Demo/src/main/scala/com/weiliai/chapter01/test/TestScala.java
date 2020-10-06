package com.weiliai.chapter01.test;

public class TestScala {

    public static void main(String[] args) {
        TestScala$.MODULE$.main(args);
    }

}

final class TestScala${

    public static final TestScala$ MODULE$;

    static {
        MODULE$ = new TestScala$();
    }

    public static void main(String[] args) {
        System.out.println("hello scala,idea...");
    }

}
