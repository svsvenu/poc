package com.venu;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;

import javax.management.Notification;
import java.util.Date;

/**
 * Created by venusurampudi on 8/19/16.
 */
public class VenuConsumer extends DefaultConsumer {


    private final VenuEndpoint endpoint;

    public VenuConsumer(VenuEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
    }


    protected int poll() throws Exception {

    System.out.println("polled");
        Exchange exchange = endpoint.createExchange();
        Date now = new Date();
        exchange.getIn().setBody("Hello World! The time is " + now);
        try {
            getProcessor().process(exchange);
        } finally {
            if (exchange.getException() != null) {
                getExceptionHandler().handleException(
                        "Error processing exchange", exchange,
                        exchange.getException());
            }
        }

        return 0;
    }





}
