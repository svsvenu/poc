package com.venu;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;

public class NettyClient {

    private static boolean initialized = false;
    public NettyClient() {

        System.out.println("Called netty client");
    }
    private static FixedChannelPool fcp;

    public static void initialize() {
        if ( !initialized ) {
            EventLoopGroup group = new NioEventLoopGroup();
            final Bootstrap cb = new Bootstrap();
            cb.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyClientHandler());
                }
            });

            cb.remoteAddress("localhost", 9999);
            cb.group(group).channel(NioSocketChannel.class);
            fcp = new FixedChannelPool(cb, new ChannelPoolHandler(), 25, 30000);
            initialized = true;
        }
        else {
            System.out.println("already initialized");
        }
    }

    public static void getClientHandler(int i) {
        Channel c = null;
        try {
            Future<Channel> f = fcp.acquire();
            c  = f.get();
            String  input = "Netty Rocks for " + i;
            c.writeAndFlush(Unpooled.copiedBuffer(input, CharsetUtil.UTF_8));
// Get the data out from the channel
     NettyClientHandler ch = (NettyClientHandler) c.pipeline().last();
            String fetched = ch.getFactorial();
            System.out.println("**Fetched " + fetched);
            if (fetched.equalsIgnoreCase(input)) {
                System.out.println("*******true********" );
            }
            else{
                System.out.println("*****************************************************false********" );

            }
            System.out.println("*******Closed********" );

        }
        catch (Exception e){e.printStackTrace();}

        finally {
            if (c != null ) {
                c.close();
                try {
                    fcp.release(c).get();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
