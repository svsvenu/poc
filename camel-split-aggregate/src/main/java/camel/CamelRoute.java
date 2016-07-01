package camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Startup
@ApplicationScoped
@ContextName("cdi-context")

public class CamelRoute extends RouteBuilder {

    @Inject
    @ContextName("cdi-context")
    CamelContext camelctx;


    @Override
    public void configure() throws Exception {

        from("file:////Users/venusurampudi/Desktop/temp")
                .split(body().tokenize("\n")).streaming().parallelProcessing()
                .to("direct:aggregate")
                .end();

        from("direct:aggregate")
               .aggregate(constant(true), new ArrayListAggregationStrategy())
                .completionSize(10)
                .to("direct:processresults");

        from("direct:processresults")
                .process(new Processor(){
                     public void process(Exchange en){

                         ArrayList<String> list = (ArrayList<String>) en.getIn().getBody(ArrayList.class);

                         System.out.println("count size is " + list.size() );
                     }

                });


    }



    private static class ArrayListAggregationStrategy implements AggregationStrategy {


        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            // put order together in old exchange by adding the order from new exchange

            if (oldExchange == null) {
                // the first time we aggregate we only have the new exchange,
                // so we just return it
                return newExchange;
            }

            String orders = oldExchange.getIn().getBody(String.class);
            String newLine = newExchange.getIn().getBody(String.class);

           System.out.println("Aggregate old orders: " + orders);
            System.out.println("Aggregate new order: " + newLine);

            // put orders together separating by semi colon
            orders = orders + ";" + newLine;
            // put combined order back on old to preserve it
            oldExchange.getIn().setBody(orders);

            // return old as this is the one that has all the orders gathered until now
            return oldExchange;
        }

    }

}