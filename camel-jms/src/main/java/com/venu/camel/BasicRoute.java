package com.venu.camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;
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
    @ContextName("cdi-context")
     CamelContext camelctx;


    @Override
    public void configure() throws Exception {


        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");


        connectionFactory.setUserName("admin");

        connectionFactory.setPassword("admin");


        camelctx = this.getContext();

        this.camelctx.getComponent("jms");

        ((JmsComponent)this.getContext().getComponent("jms")).setConnectionFactory(connectionFactory);

        if (camelctx == null){

            System.out.println("camelctx is null");
        }
        else {

            System.out.println("default components are" + camelctx.getComponentNames());


        }

        from("timer://foo?fixedRate=true&period=30000").transform(body().prepend("Hi 4 venu")).log(body().toString())
                .log(body().toString())
                .to("jms:/venuq")

        .log(body().toString());



    }
 
}