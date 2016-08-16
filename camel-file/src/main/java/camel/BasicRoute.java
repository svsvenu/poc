package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.model.dataformat.BindyType;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;

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

        PropertiesComponent pc = (PropertiesComponent)camelctx.getComponent("properties");


        System.out.println (" props " + pc);

        from("file:///Users/venusurampudi/Desktop/camel/?scheduler=spring&scheduler.cron=*/10 * * * * *")

                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(order);
                           Long randomNumber = new Date().getTime();
                        System.out.println(randomNumber.longValue() % 5);
                            if (randomNumber.longValue() % 5 != 0) {
                                throw new Exception("sample ");
                            }
                    }
                })
                .log(body().toString())


        .log(body().toString());

    }
 
}