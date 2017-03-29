package com.venu;

/**
 * Created by venusurampudi on 3/28/17.
 */
public interface IHelloWorldMXBean {

    /**
     * Read only attribute
     */
    long getCount();

    /**
     * Read write attribute
     */
    void setWelcomeMessage(String message);

    String getWelcomeMessage();


    String sayHello(String name);

}
