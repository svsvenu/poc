package com.venu;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;

public class ChannelPoolHandler implements io.netty.channel.pool.ChannelPoolHandler {
    public void channelReleased(Channel channel) throws Exception {
        System.out.println ("Channel released ");
    }

    public void channelAcquired(Channel channel) throws Exception {
        System.out.println ("channel acquired");
    }

    public void channelCreated(Channel channel) throws Exception {
        System.out.println ("Channel  created in pool ");
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new NettyClientHandler());
    }
}
