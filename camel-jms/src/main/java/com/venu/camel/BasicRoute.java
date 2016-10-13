package com.venu.camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spi.EventNotifier;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Vector;

@Startup
@ApplicationScoped
@ContextName("cdi-context")
 
public class BasicRoute extends RouteBuilder {

    @Inject
    CamelContext camelctx;

    @Override
    public void configure() throws Exception {

        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");


        connectionFactory.setUserName("admin");

        connectionFactory.setPassword("admin");


        ((JmsComponent)this.getContext().getComponent("jms")).setConnectionFactory(connectionFactory);

        ((JmsComponent)this.getContext().getComponent("jms")).setRecoveryInterval(3000);

        ((JmsComponent)this.getContext().getComponent("jms")).setTransactionManager(new JmsTransactionManager(connectionFactory));

     //   ((JmsComponent)this.getContext().getComponent("jms")).setTransacted(true);

        JmsConfiguration jmsConfig = new JmsConfiguration();

        jmsConfig.setConnectionFactory(connectionFactory);
        jmsConfig.setTransactionManager(new JmsTransactionManager(connectionFactory));

                ((JmsComponent) ((JmsComponent) this.getContext().getComponent("jms"))).setConfiguration(jmsConfig);

        Notifications nt = new Notifications();

        getContext().getManagementStrategy().addEventNotifier( nt);

        nt.start();

        System.out.println("added notifier for context " + getContext().getName());

        System.out.println(getContext().getManagementStrategy().getEventNotifiers());


      //  getContext().stop();

      //  getContext().start();



/*
        from("timer://foo?fixedRate=true&period=30000").transform(body().prepend("Sent messge via JMS")).log(body().toString())
                .log(body().toString())
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody("xxxxxxx");
                    }
                })
                .to("jms:/venuq")
                .log(body().toString());

        from("jms:/venuq")
           //     .transacted()
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        //       throw new Exception("exceeption after consumption");
                    }
                })
                .log("Received message" + body().toString());

                */

        from("timer://foo?fixedRate=true&period=30000").transform(body().prepend("Sent to reqrespq")).log(body().toString())
                .log(body().toString())
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody("xxxxxxx");
                    }
                })
                .to("jms:/reqrespq?exchangePattern=InOut")
                .log("received message after")
                .log(body().toString());

        from("jms:/reqrespq").log("Received message" + body().toString())
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Thread.sleep(5000l);
                        System.out.println("done sleeping");
                    }
                })
        .transform(simple(("hello")));


        Field f = ClassLoader.class.getDeclaredField("classes");
        f.setAccessible(true);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        System.out.println("class loader name is  " + classLoader.getClass().getName());


        Vector<Class> classes =  (Vector<Class>) f.get(classLoader);

        for (Class clas : classes) {

            System.out.println("classes are " + clas.getName());
        }



    }

 
}