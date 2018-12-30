package com.venu;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static AtomicInteger counter = new AtomicInteger(0);
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("Established connection: ");
    }
    @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf inBuffer = (ByteBuf) msg;
            String received = inBuffer.toString(CharsetUtil.UTF_8);
            System.out.println("Server received: " + received);
         //   Thread.sleep(10000);
           // ctx.writeAndFlush(Unpooled.copiedBuffer( received, CharsetUtil.UTF_8));

        ctx.write(Unpooled.copiedBuffer( received, CharsetUtil.UTF_8));

        ctx.flush();

       // Thread.sleep(10000);


        ctx.write(Unpooled.copiedBuffer( "2nd attempt", CharsetUtil.UTF_8));

        ctx.flush();

       // Thread.sleep(10000);

        ctx.write(Unpooled.copiedBuffer( "DONEz", CharsetUtil.UTF_8));

        ctx.flush();




    }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println(" channel read complete " );
          //  ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
            //
            //
            ctx.write(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
            decrement();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
            decrement();
        }

        public void channelActive(ChannelHandlerContext ctx) throws java.lang.Exception {
            increment();
        System.out.println(" channel activated counter is " + getCount() );

    }

    private synchronized AtomicInteger getCount() {
            return counter;
    }

    private synchronized void increment() {
        counter.incrementAndGet();
    }

    private synchronized void decrement() {
            counter.decrementAndGet();
     }

    }