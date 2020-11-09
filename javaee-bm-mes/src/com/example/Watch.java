package com.example;

import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Watch {
    private static Stack<Integer> stack = new Stack<>();
    private static int counter = 0;


    public static void push() {
        IntStream.range(0, 10)
                .map(i -> ++counter)
                .forEach(i -> stack.add(i));
    }

    public static Integer pop() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception ignore) {
        }
        if (stack.isEmpty()) {
            return null;
        } else if (stack.size() % 7 == 0) {
            stack.pop();
            throw new RuntimeException();
        }
        return stack.pop();
    }
}
