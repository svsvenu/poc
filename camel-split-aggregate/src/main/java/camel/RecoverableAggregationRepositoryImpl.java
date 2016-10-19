package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.spi.RecoverableAggregationRepository;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by venusurampudi on 10/18/16.
 */
public class RecoverableAggregationRepositoryImpl implements RecoverableAggregationRepository {
    public Set<String> scan(CamelContext camelContext) {
        return null;
    }

    public Exchange recover(CamelContext camelContext, String s) {
        return null;
    }

    public void setRecoveryInterval(long l, TimeUnit timeUnit) {

    }

    public void setRecoveryInterval(long l) {



    }

    public long getRecoveryIntervalInMillis() {
        return 30000;
    }

    public void setUseRecovery(boolean b) {

    }

    public boolean isUseRecovery() {
        return true;
    }

    public void setDeadLetterUri(String s) {

    }

    public String getDeadLetterUri() {
        return null;
    }

    public void setMaximumRedeliveries(int i) {

    }

    public int getMaximumRedeliveries() {
        return 5;
    }

    public Exchange add(CamelContext camelContext, String s, Exchange exchange) {

        System.out.println("called add " + s);

        System.out.println("Body in add is  " + exchange.getIn().getBody());


        return null;
    }

    public Exchange get(CamelContext camelContext, String s) {

        System.out.println("called get " + s);


        return null;
    }

    public void remove(CamelContext camelContext, String s, Exchange exchange) {

        System.out.println("called remove");


    }

    public void confirm(CamelContext camelContext, String s) {

        System.out.println("called confirm");


    }

    public Set<String> getKeys() {

        System.out.println("called confirm");

        return null;
    }
}
