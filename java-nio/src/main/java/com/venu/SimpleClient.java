package com.venu;

/**
 * Created by venusurampudi on 10/28/17.
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;

public class SimpleClient  {
    // The host:port combination to connect to

    public static void main(String[] args) throws Exception {

        SocketChannel sc = SocketChannel.open();

        sc.connect(new InetSocketAddress("localhost", 9090));


        for (int i = 0; i<10 ; i ++ ) {

            String newData = "aSending from client " + i ;


            ByteBuffer bb = ByteBuffer.allocate(48);
            bb.clear();
            bb.put(newData.getBytes());
            bb.flip();

            sc.write(bb);

            Thread.sleep(1000);

        }

        sc.close();

        System.out.println("done writing");


    }

}