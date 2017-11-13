package com.venu.concurrency;

/**
 * Created by venusurampudi on 10/30/17.
 */
public class ConditionExample {
    public static void main(String[] args) throws InterruptedException {
        SharedFiFoQueue sharedQueue = new SharedFiFoQueue(3);

        //Create a producer and a consumer.
        Thread producer = new Producer(sharedQueue);
        Thread consumer = new Consumer(sharedQueue);
        Thread consumer1 = new Consumer(sharedQueue);
        Thread consumer2 = new Consumer(sharedQueue);
        Thread consumer3 = new Consumer(sharedQueue);


        //Start both threads.
      //  producer.start();
        consumer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        producer.start();

System.out.println("kicked off threads");

        //Wait for both threads to terminate.
        producer.join();
        consumer.join();
        consumer1.join();
        consumer2.join();
        consumer3.join();

    }
}