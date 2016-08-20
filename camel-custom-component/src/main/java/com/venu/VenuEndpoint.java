package com.venu;

import org.apache.camel.*;
import org.apache.camel.impl.DefaultEndpoint;

import java.util.Map;

/**
 * Created by venusurampudi on 8/19/16.
 */
public class VenuEndpoint extends DefaultEndpoint {

    private String key1;

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public VenuEndpoint() {
    }

    public VenuEndpoint(String uri, VenuComponent component) {
        super(uri, component);
    }

    public Producer createProducer() throws Exception {
        return new VenuProducer(this);
    }
    public Consumer createConsumer(Processor processor) throws Exception {
        return new VenuConsumer(this, processor);
    }
    public boolean isSingleton() {
        return true;
    }
}
