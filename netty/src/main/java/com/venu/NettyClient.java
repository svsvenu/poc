package com.venu;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import org.apache.commons.lang3.reflect.FieldUtils;
import sun.misc.Unsafe;
import org.apache.log4j.Logger;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyClient {

    private static boolean initialized = false;
    static Logger logger = Logger.getLogger(NettyClient.class);
    public NettyClient() {

        System.out.println("Called netty client");
    }
    private static FixedChannelPool fcp;

    public static void initialize() {
        if ( !initialized ) {
            EventLoopGroup group = new NioEventLoopGroup(2);
            final Bootstrap cb = new Bootstrap();
            cb.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyClientHandler());
                }
            });



            cb.remoteAddress("localhost", 9999);
            cb.option(ChannelOption.ALLOCATOR,PooledByteBufAllocator.DEFAULT);
            cb.group(group);
            cb.channel(NioSocketChannel.class);
            fcp = new FixedChannelPool(cb, new ChannelPoolHandler(), 5, 30000);
            initialized = true;
        }
        else {
            System.out.println("already initialized");
        }
    }

    private Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public static void getClientHandler(int i) {
        Channel c = null;
        try {
            Future<Channel> f = fcp.acquire();
            c  = f.get();

            logger.info("acquired channel ");
            String  input = "Netty Rocks for " + i;
            c.writeAndFlush(Unpooled.copiedBuffer(input, CharsetUtil.UTF_8));

            for ( int j=0; j<10; j++) {
                c.eventLoop().submit(new RunnableClass(c));
            }

            if ( c.eventLoop().inEventLoop() ){
                System.out.println("In event loop Thread id is " + Thread.currentThread().getId());

            }
            else {

                System.out.println("Not In event loop  " + Thread.currentThread().getId());

            }


// Get the data out from the channel
     NettyClientHandler ch = (NettyClientHandler) c.pipeline().last();
            String fetched = ch.getFactorial();



        }
        catch (Exception e){e.printStackTrace();}

        finally {
            if (c != null ) {
              //  c.close();
                try {
                    fcp.release(c).get();
                    System.out.println("Released " + c.id());

                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static int getPoolCount()  {

        return fcp.acquiredChannelCount();
    }
}
