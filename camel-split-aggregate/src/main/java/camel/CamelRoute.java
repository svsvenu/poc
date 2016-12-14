package camel;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
                .maximumRedeliveries(2).continued(false
        );

       String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

        RecoverableAggregationRepositoryImpl agg = new RecoverableAggregationRepositoryImpl();

       // from("file:////Users/venusurampudi/Desktop/temp")

        from("timer://foo?fixedRate=true&period=30000")
                .log("Kicked off scheduler")

                .split().method("customSplitter" , "foo").streaming()

                //        .split().tokenizeXML("article").streaming()

          //    .split(body().t("<article>"))

                        //   .split(body().tokenize("\n")).streaming()
                .to("bean:processingBean?method=doSomething")
                        //.parallelProcessing()
                .to("direct:aggregate")
                .end();

        from("direct:aggregate")
             //   .aggregate(simple("${id}"), new ArrayListAggregationStrategy())
                .aggregate(constant(format), new ArrayListAggregationStrategy())
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

                System.out.println("old exchange is null");


                list = new ArrayList<Object>();
                list.add(newBody);
                newExchange.getIn().setBody(list);
                return newExchange;
            } else {

              //  System.out.println("new body is " + newBody.toString());


                list = oldExchange.getIn().getBody(ArrayList.class);
                list.add(newBody);
                return oldExchange;
            }
        }

    }

}