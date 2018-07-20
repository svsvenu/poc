package com.venu;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ChannelPoolHandler implements io.netty.channel.pool.ChannelPoolHandler {
    public void channelReleased(Channel channel) throws Exception {
        System.out.println ("Channel released count is ");
    }

    public void channelAcquired(Channel channel) throws Exception {
        System.out.println ("pool acquired");

    }

    public void channelCreated(Channel channel) throws Exception {
        System.out.println ("Channel  created in pool ");
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new ClientHandler());


    }
}