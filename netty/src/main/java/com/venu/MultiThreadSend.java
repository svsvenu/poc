package com.venu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadSend {
    public static void main(String[] args) {

        NettyClient.initialize();
        ExecutorService es = Executors.newFixedThreadPool(100);
        for (int i= 0; i< 100; i++) {
            System.out.println(i);
            es.submit(new CallableClient(i));
        }
        es.shutdown();
    }
}
