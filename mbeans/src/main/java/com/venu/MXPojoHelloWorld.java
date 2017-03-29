package com.venu;

/**
 * Created by venusurampudi on 3/28/17.
 */
public class MXPojoHelloWorld implements IHelloWorldMXBean {
    public long getCount() {
        return 0;
    }

    public void setWelcomeMessage(String message) {

    }

    public String getWelcomeMessage() {
        return null;
    }

    public String sayHello(String name) {
        return "Hello beanie";
    }
}
