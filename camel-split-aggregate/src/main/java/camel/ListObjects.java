package camel;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

import java.util.List;

/**
 * Created by venusurampudi on 12/12/16.
 */
public class ListObjects {

    public static void main(String[] args){

        queryPersonByPhone();
    }

    private static void queryPersonByPhone() {

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer()
                .host("localhost")
                .port(11222)
                .marshaller(new ProtoStreamMarshaller());  // The Protobuf based marshaller is required for query capabilities

        RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());

        RemoteCache<String, Object> repoCache = CacheOperations.getCache();


        QueryFactory qf = Search.getQueryFactory(repoCache);
        Query query = qf.from(CamelExchange.class)
                .having("name").like("%").toBuilder()
                .build();

        List<CamelExchange> results = query.list();
        System.out.println("Found " + results.size() + " matches:");
        for (CamelExchange p : results) {
            System.out.println(">> " + p);
        }
    }

}
