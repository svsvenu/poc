package com.venu.concurrency;

/**
 * Created by venusurampudi on 10/30/17.
 */
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFoQueue {

    private Object[] elems = null;
    private HashMap<String, String> map = new HashMap<String,String>();
    private int capacity = 0;
    private int current = 0;
    private int placeIndex = 0;
    private int removeIndex = 0;

    private final Lock lock = new ReentrantLock();


    private final Condition isEmpty = lock.newCondition();
    private final Condition isFull = lock.newCondition();

    public SharedFiFoQueue(int capacity) {
        this.elems = new Object[capacity];
        this.capacity = capacity;
    }

    public void add(String elem) throws InterruptedException {
        lock.lock();
        if(map.keySet().size() == capacity) {
            isFull.await();
        System.out.println("In full wait");
        }

        map.put(elem, elem);

//        elems[placeIndex] = elem;

        Thread.sleep(5000);


        //We need the modulo, in order to avoid going out of bounds.
  //      placeIndex = (placeIndex + 1) % elems.length;

    //    ++current;

        System.out.println("Added object " + elem + " " + Thread.currentThread().getId());

        //Notify the consumer that there is data available.
        isEmpty.signal();

        lock.unlock();
    }

    public Object remove() throws InterruptedException {
        Object elem = null;

        lock.lock();
        while(map.keySet().size() <= 0) {
            isEmpty.await();
            System.out.println("In empty wait");


        }

        for (String key : map.keySet()) {

            System.out.println("Removed " + key);


            map.remove(key);

            break;
        }

      //  Thread.sleep(5000);

        //elem = elems[removeIndex];

        //We need the modulo, in order to avoid going out of bounds.
       // removeIndex = (removeIndex + 1) % elems.length;

        //--current;

        //Notify the producer that there is space available.
        isFull.signal();

        lock.unlock();

        return elem;
    }
}