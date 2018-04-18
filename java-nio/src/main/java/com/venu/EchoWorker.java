package com.venu;

/**
 * Created by venusurampudi on 10/28/17.
 */
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class EchoWorker implements Runnable {
    private List queue = new LinkedList();

    public void processData(NioServer server, SocketChannel socket, byte[] data, int count) {
        byte[] dataCopy = new byte[count];
        System.arraycopy(data, 0, dataCopy, 0, count);
        synchronized(queue) {
            System.out.println("Added bytes size is " + queue.size());
            queue.add(new ServerDataEvent(server, socket, dataCopy));
            queue.notify();
        }
    }

    public void run() {
        ServerDataEvent dataEvent;

        while(true) {
            // Wait for data to become available
            synchronized(queue) {
             //   while(queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        System.out.println(" Interrupted " );
                    }
              //  }
                // dataEvent = (ServerDataEvent) queue.remove(0);
              //  dataEvent = (ServerDataEvent) queue.get(0);
                System.out.println("removing " + queue.size());
            }

            // Return to sender
           // dataEvent.server.send(dataEvent.socket, dataEvent.data);
        }
    }
}