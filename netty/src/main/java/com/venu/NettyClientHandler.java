package com.venu;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NettyClientHandler extends SimpleChannelInboundHandler {

    final BlockingQueue<String> answer = new LinkedBlockingQueue<String>();

    public String getFactorial() {
        boolean interrupted = false;
                try {
                       for (;;) {
                                try {
                                         return answer.take();
                                    } catch (InterruptedException ignore) {
                                        interrupted = true;
                                    }
                           }
                     } finally {
                       if (interrupted) {
                            Thread.currentThread().interrupt();
                         }
                     }
             }

    public NettyClientHandler(){

        System.out.println("INstantiated client handler");
    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext){
        System.out.println("Cient channel active called  " ) ;
      //  channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8));
    }

    public void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8) + in.getClass().getName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause){
        cause.printStackTrace();
        channelHandlerContext.close();
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf inBuffer = (ByteBuf) o;
        String received = inBuffer.toString(CharsetUtil.UTF_8);
        System.out.println("Channel id is  " + channelHandlerContext.channel().id() ) ;

     //   System.out.println("Clinet channel read 0 received: " + received + "clas is " + inBuffer.getClass().getName());
        System.out.println("in event loop is  " + channelHandlerContext.channel().eventLoop().inEventLoop() );

        while (inBuffer.isReadable()) {
            byte b = inBuffer.readByte();
            byte[] bytes  =  new byte[1];
            bytes[0] = b;
            String character = new String(bytes, "UTF-8");

            if (character.equalsIgnoreCase("Z")) {

                answer.add(received);

            }

        //    System.out.println(new String(bytes, "UTF-8"));

        }
       // answer.add(received);

    }
}