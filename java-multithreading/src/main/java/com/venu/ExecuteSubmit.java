package com.venu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteSubmit {

    public static void main(String[] args){

        Runnable rn = new Runnable() {
            @Override
            public void run()  {
              try {
                  Thread.sleep(10000);
              }
              catch (Exception e){}
                System.out.println("In runnable thread id is " + Thread.currentThread().getId());
            }
        };

        System.out.println("In main thread id is " + Thread.currentThread().getId());


        ExecutorService es = new VenuExecutor();

        es.execute(rn);

        System.out.println("After exeute call " + Thread.currentThread().getId());

    }
}
