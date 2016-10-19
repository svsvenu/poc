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

        // in case of io exception then try to redeliver up till 2 times
// (do not use any delay due faster unit testing)
        onException(Exception.class)
                .maximumRedeliveries(2).redeliveryDelay(0);

        RecoverableAggregationRepositoryImpl agg = new RecoverableAggregationRepositoryImpl();

        from("file:////Users/venusurampudi/Desktop/temp")
                .split(body().tokenize("\n")).streaming()
                .to("bean:processingBean?method=doSomething")
                        //.parallelProcessing()
                .to("direct:aggregate")
                .end();

        from("direct:aggregate")
             //   .aggregate(simple("${id}"), new ArrayListAggregationStrategy())
                              .aggregate(constant(true), new ArrayListAggregationStrategy())
                .completionSize(5)
                .aggregationRepository(agg)

                .log("about to call agg")

                .to("direct:processresults");

        from("direct:processresults")
                .process(new Processor(){
                     public void process(Exchange en){

                         System.out.println("count size is "  );


                         ArrayList<String> list = (ArrayList<String>) en.getIn().getBody(ArrayList.class);

                     }

                });


    }



    private static class ArrayListAggregationStrategy implements AggregationStrategy {


        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            Object newBody = newExchange.getIn().getBody();
            ArrayList<Object> list = null;
            if (oldExchange == null) {
                list = new ArrayList<Object>();
                list.add(newBody);
                newExchange.getIn().setBody(list);
                return newExchange;
            } else {
                list = oldExchange.getIn().getBody(ArrayList.class);
                list.add(newBody);
                return oldExchange;
            }
        }

    }

}