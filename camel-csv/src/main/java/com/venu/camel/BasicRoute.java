package com.venu.camel;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.CsvDataFormat;
import com.venu.camel.PurchaseOrder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Startup
@ApplicationScoped
@ContextName("cdi-context")
 
public class BasicRoute extends RouteBuilder {

    @Inject
    @ContextName("cdi-context")
     CamelContext camelctx;

//    CSVConfig custom = new CSVConfig();
//    custom.setDelimiter(';');
//    custom.setEndTrimmed(true);
//    custom.addField(new CSVField("id"));
//    custom.addField(new CSVField("customerId"));
//    custom.addField(new CSVField("date"));
//    custom.addField(new CSVField("item"));
//    custom.addField(new CSVField("amount"));
//    custom.addField(new CSVField("description"));
//
//    CsvDataFormat myCsv = new CsvDataFormat();
//
//
//    myCsv.setConfig(custom);
//    myCsv.setAutogenColumns(false);



  //  order.setName("venu");


    @Override
    public void configure() throws Exception {


        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");


        connectionFactory.setUserName("admin");

        connectionFactory.setPassword("admin");


        camelctx = this.getContext();

        this.camelctx.getComponent("jms");

        ((JmsComponent)this.getContext().getComponent("jms")).setConnectionFactory(connectionFactory);



        //    camelctx.addComponent("jms",
     //           (org.apache.camel.Component) JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        if (camelctx == null){

            System.out.println("camelctx is null");
        }
        else {

            System.out.println("default components are" + camelctx.getComponentNames());


        }


        final PurchaseOrder order = new PurchaseOrder();

        order.setName("big po");
        order.setAmount(10);
        order.setPrice(new BigDecimal(10));


        from("timer://foo?fixedRate=true&period=30000").transform(body().prepend("Hi 3 venu")).log(body().toString())
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(order);
                    }
                })
                .log(body().toString())

               .marshal().bindy(BindyType.Csv, "com.venu")
                .to("jms:/venuq")
                .to("file:/Users/venusurampudi/test.csv")

        .log(body().toString());



    }
 
}