package com.venu;

/**
 * Created by venusurampudi on 1/4/18.
 */
public class Notifier implements Runnable {

    private Message message;

    public void run() {

        synchronized (message) {

            System.out.println("In run of notifier " + Thread.currentThread().getName());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            message.notify();

            System.out.println("Notification complete");


        }

    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
