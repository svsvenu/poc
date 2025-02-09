package com.venu;

public  class Worker implements Runnable{

    Integer count ;

    public Worker(Integer count){
        this.count = count;
    }


    @Override
    public void run() {

        count = count + 1;

    }
}
