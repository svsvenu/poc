package com.venu;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by venusurampudi on 8/19/16.
 */
public class VenuProducer extends DefaultProducer {

    private static final transient Log LOG = LogFactory.getLog(VenuProducer.class);

    private VenuEndpoint endpoint;

    public VenuProducer(VenuEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }
    public void process(Exchange exchange) throws Exception {
        System.out.println("in custom" + exchange.getIn().getBody() + endpoint.getKey1());
    }


}
