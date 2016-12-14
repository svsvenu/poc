package camel;

import org.apache.camel.Exchange;

import javax.inject.Named;

/**
 * Created by venusurampudi on 10/18/16.
 */
@Named
public class ProcessingBean {


    public void doSomething(Exchange exchange) throws Exception{
        // process the exchange

  //  String line = (String) exchange.getIn().getBody();

      //  if (line.equalsIgnoreCase("5,2,3,4,5")) {

            System.out.println("Line is " + exchange.getIn().getBody().toString());

          //  throw new Exception("Exceptin");

      //  }

       //  exchange.getIn().setBody("Bye World");

      //  throw new Exception(" thrown ");
    }

}