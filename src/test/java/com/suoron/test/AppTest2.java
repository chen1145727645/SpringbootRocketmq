package com.suoron.test;

public class AppTest2 {
    public static void main(String[] args) {

        TestInterface testInterface = ((TestInterface)(str)->{
            return "hello " + str;
        });

        System.out.println(testInterface.sayHello("xxxxx"));
    }
}
