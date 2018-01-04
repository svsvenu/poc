package com.venu;

/**
 * Created by venusurampudi on 1/4/18.
 */
public class Waiter implements Runnable {

    private Message message;

    public void  run() {
        System.out.println("In run of waiter" + Thread.currentThread().getName());

        synchronized (message) {
            try {
                System.out.println("In run of waiter synchronized");

                message.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("wait is done");

    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
