package com.venu.camel;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
import java.math.BigDecimal;

@Startup
@ApplicationScoped
@ContextName("cdi-context")


public class BasicRoute extends RouteBuilder {

    @Inject
     CamelContext camelctx;

    @Override
    public void configure() throws Exception {

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
                .to("file:/Users/venusurampudi/test.csv")

        .log(body().toString());

    }
 
}