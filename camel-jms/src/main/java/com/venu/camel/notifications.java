package com.venu.camel;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeSendingEvent;
import org.apache.camel.management.event.ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.apache.log4j.*;

import java.util.Date;
import java.util.EventObject;

/**
 * Created by venusurampudi on 9/10/16.
 */
public class Notifications extends EventNotifierSupport {

    Logger logger = Logger.getLogger(Notifications.class);

    public void notify(EventObject event) throws Exception {



      //  System.out.println("received event");

        if (event instanceof ExchangeSentEvent) {
            ExchangeSentEvent sent = (ExchangeSentEvent) event;

         //   logger.info("Took " + sent.getTimeTaken() + " millis to send to: " + sent.getEndpoint());

        }

        if (event instanceof ExchangeSendingEvent) {
            ExchangeSendingEvent sent = (ExchangeSendingEvent) event;

            ((ExchangeSendingEvent) event).getExchange().getIn();

            logger.info("ExchangeSendingEvent " + ((ExchangeSendingEvent) event).getEndpoint());

        }

        if (event instanceof ExchangeSentEvent) {
            ExchangeSentEvent sent = (ExchangeSentEvent) event;

            ((ExchangeSentEvent) event).getExchange().getIn();

            logger.info("ExchangeSentEvent " + ((ExchangeSentEvent) event).getEndpoint());

        }


        if (event instanceof ExchangeCompletedEvent) {
            ExchangeCompletedEvent exchangeCompletedEvent = (ExchangeCompletedEvent) event;
            Exchange exchange = exchangeCompletedEvent.getExchange();
            String routeId = exchange.getFromRouteId();
            Date created = ((ExchangeCompletedEvent) event).getExchange().getProperty(Exchange.CREATED_TIMESTAMP, Date.class);
            // calculate elapsed time
            Date now = new Date();
            long elapsed = now.getTime() - created.getTime();
          //  log.info(">>> Took " + elapsed + " millis for the exchange on the route : " + routeId);
        }

    }

    public boolean isEnabled(EventObject event) {
        // we only want the sent events
        return true;
    }

    protected void doStart() throws Exception {
        logger.info("starg");
    }

    protected void doStop() throws Exception {
        logger.info("stop");
    }

}

