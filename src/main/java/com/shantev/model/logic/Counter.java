package com.shantev.model.logic;

public class Counter {
    public static int count = 0;

    public static int getCount() {
        return ++count;
    }
}
