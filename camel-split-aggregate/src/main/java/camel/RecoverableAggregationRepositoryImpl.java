package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultExchangeHolder;
import org.apache.camel.spi.RecoverableAggregationRepository;
import org.infinispan.client.hotrod.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

import java.io.*;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by venusurampudi on 10/18/16.
 */
public class RecoverableAggregationRepositoryImpl implements RecoverableAggregationRepository {

   // private final ConcurrentMap<String, bytes> cache = new ConcurrentHashMap<String, Exchange>();


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

    public Exchange add(CamelContext camelContext, String key, Exchange exchange) {

        DefaultExchangeHolder deh = DefaultExchangeHolder.marshal(exchange, true);

        object2Bytes(deh);

        CamelExchange ce = new   CamelExchange();

        ce.setName(key);

        ce.setBytes( object2Bytes(deh));


        CacheOperations.getCache().put(key, ce);

        printContents();

        return exchange;

    }

    public Exchange get(CamelContext camelContext, String key) {

        System.out.println("called get " + key);

        if ( CacheOperations.getCache().get(key) == null ){

            System.out.println("key is null");

            return null;
        }

        System.out.println("Key is not null " + CacheOperations.getCache().get(key).getClass());

      //  DefaultExchangeHolder deh = (DefaultExchangeHolder) bytes2Object((byte[]) CacheOperations.getCache().get(key));

        CamelExchange ce = (CamelExchange)CacheOperations.getCache().get(key);

        DefaultExchangeHolder deh = (DefaultExchangeHolder) bytes2Object( ce.getBytes() );

        Exchange answer = new DefaultExchange(camelContext);

        deh.unmarshal(answer,deh);


        printContents();

        return  answer;

    }

    public void remove(CamelContext camelContext, String key, Exchange exchange) {

        System.out.println("called remove for " + key);

        printContents();


        CacheOperations.getCache().remove(key);

        printContents();




    }

    public void confirm(CamelContext camelContext, String s) {

        System.out.println("called confirm");


    }

    public Set<String> getKeys() {

        System.out.println("called confirm");

        return null;
    }

    public byte[] object2Bytes(Object o){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] outputBytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(o);
            out.flush();
             outputBytes = bos.toByteArray();

        } catch (IOException ex) {
            ex.printStackTrace();
            }

        return outputBytes;

    }

    public Object bytes2Object(byte[] objectBytes){

        ByteArrayInputStream bis = new ByteArrayInputStream(objectBytes);
        ObjectInput in = null;

        Object o = null;
        try {
            in = new ObjectInputStream(bis);
             o = in.readObject();
        }
        catch (Exception e){

            e.printStackTrace();
        }

        return o;

    }

    private void printContents(){

        QueryFactory qf = Search.getQueryFactory(CacheOperations.getCache());
        Query query = qf.from(CamelExchange.class)
                .having("name").like("%").toBuilder()
                .build();

        System.out.println("query built ok"+ query.list());
    }

}
