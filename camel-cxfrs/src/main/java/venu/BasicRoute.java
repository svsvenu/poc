package com.venu;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.impl.DefaultCamelContext;
import venu.PurchaseOrder;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;

@Startup
@ApplicationScoped
@ContextName("cdi-context")


public class BasicRoute extends RouteBuilder {

    @Inject
     CamelContext camelctx;



    @Override
    public void configure() throws Exception {



    System.out.println("component is " + this.getContext().getComponent("venu"));

        final PurchaseOrder order = new PurchaseOrder();

        order.setName("big po");
        order.setAmount(10);
        order.setPrice(new BigDecimal(10));

   //     camelctx.addComponent("venu", vc);

   //     camelctx.addEndpoint("venu", new VenuEndpoint());

        from("timer://foo?fixedRate=true&period=5000")
               .setHeader("type", constant("json"))
                .setHeader(Exchange.ACCEPT_CONTENT_TYPE, constant("application/json"))

                .setHeader("CamelHttpMethod", constant("GET"))


                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody("");
                    }
                })
                        .log(body().toString())

                        .to("cxfrs:http://localhost:8080/jboss-json-1.0-SNAPSHOT/rest/message/get?resourceClasses=venu.PurchaseOrder")
                .log(body().toString());

    }

    public static void main(String[] args) throws Exception{

        DefaultCamelContext dc = new DefaultCamelContext();


        dc.addRoutes(new BasicRoute());

        dc.start();


    }
 
}