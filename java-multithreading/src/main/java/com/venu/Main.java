package com.venu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by venusurampudi on 1/4/18.
 */
public class Main {

    public static void main(String args) {

     //   ExecutorService es = new ThreadPoolExecutor(10);

        Message message = new Message();

            Notifier nf = new Notifier();
            nf.setMessage(message);

        Thread notifierThread = new Thread(nf,"notifier");

       // notifierThread.setDaemon(true);


        Waiter wf = new Waiter();
            wf.setMessage(message);

        Thread waiter = new Thread ( wf,"waiter");

     //       waiter.setDaemon(true);

        waiter.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        notifierThread.start();

      //  waiter.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
