package com.venu;

import io.netty.channel.Channel;

public class RunnableClass implements Runnable {

    public Channel c ;

    public RunnableClass(Channel c )
    {
        this.c = c;
    }
    public void run() {

        System.out.println("Hey eventloop run this " +  "Am in in event loop?  " + c.eventLoop() + " channel is " + c.id() + "-" + c.eventLoop().inEventLoop() + " Thread is " + Thread.currentThread().getId());


    }
}
