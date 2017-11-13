package com.venu;

/**
 * Created by venusurampudi on 10/28/17.
 */

public class RspHandler {

    private String name;

    public RspHandler (String name) {

        this.name = name;
    }

    public String getName(){

        return this.name;
    }


    private byte[] rsp = null;

    public synchronized boolean handleResponse(byte[] rsp) {
        this.rsp = rsp;

        System.out.println("before notify");

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notify();
        return true;
    }

    public synchronized void waitForResponse() {
        while(this.rsp == null) {
            try {

               System.out.println("GOint into wait");
                this.wait();
            } catch (InterruptedException e) {

                e.printStackTrace();

            }


        }

        System.out.println("Received : " + new String(this.rsp) + "Thread-->" + Thread.currentThread().getId());
    }
}