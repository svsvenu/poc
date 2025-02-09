package com.venu;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Threads {

    public static void main(String[] args){

        Worker w1 = new Worker (10);

        IntStream.range(0,10).forEach(n-> System.out.println(n));

    }
}
