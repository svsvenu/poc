package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
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

        final PurchaseOrder order = new PurchaseOrder();

        order.setName("big po");
        order.setAmount(10);
        order.setPrice(new BigDecimal(10));

       from ("timer://sendToRoute?fixedRate=true&period=30000").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setBody("sending to exchange asynchronously");
            }
        }).log("About to send to long waiting route").wireTap("direct:customendpoint").log("Phew returned ");

      //  from("direct:log").log("received");

        from("direct:customendpoint").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setBody(order);
                Thread.sleep(5000);

            }
        }).log("End of sleep");

    /*    from("timer://foo?fixedRate=true&period=30000").transform(body().prepend("Hi 4 venu")).log(body().toString())
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(order);
                    }
                })
                .log(body().toString())

           //    .marshal().bindy(BindyType.Csv, "com.venu")
           //     .to("file:/Users/venusurampudi/test.csv")

        .log(body().toString()); */

    }
 
}