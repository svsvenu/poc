package com.venu;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.BindyType;

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

        VenuComponent vc = new VenuComponent(camelctx);

        this.getContext().addComponent("venu",new VenuComponent(camelctx));

    System.out.println("component is " + this.getContext().getComponent("venu"));

   //     camelctx.addComponent("venu", vc);

   //     camelctx.addEndpoint("venu", new VenuEndpoint());

        from("timer://foo?fixedRate=true&period=5000")
                .to("venu://?key1=test")
        .log(body().toString());

    }

    public static void main(String[] args) throws Exception{

        DefaultCamelContext dc = new DefaultCamelContext();
        dc.addComponent("venu" , new VenuComponent(dc));


        dc.addRoutes(new BasicRoute());

        dc.start();


    }
 
}