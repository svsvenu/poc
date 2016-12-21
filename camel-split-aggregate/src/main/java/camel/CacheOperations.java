package camel;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.protostream.FileDescriptorSource;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;

import java.io.*;
import java.util.List;

/**
 * Created by venusurampudi on 11/30/16.
 */
public class CacheOperations {

    private static RemoteCache<String, Object> repoCache = null;

    private static RemoteCacheManager cacheManager;

    private static final String PROTOBUF_DEFINITION_RESOURCE = "/quickstart/exchange.proto";

    public static void setup() throws Exception {

        System.out.println("Begin constructing cache operations");

        final String host = "localhost";
        final int hotrodPort = 11222;
        final String cacheName = "default";  // The name of the address book  cache, as defined in your server config.

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer()
                .host(host)
                .port(hotrodPort)
                .marshaller(new ProtoStreamMarshaller());  // The Protobuf based marshaller is required for query capabilities
        cacheManager = new RemoteCacheManager(builder.build());

        repoCache = cacheManager.getCache("default");
        if (repoCache == null) {
            throw new RuntimeException("Cache '" + cacheName + "' not found. Please make sure the server is properly configured");
        }

        registerSchemasAndMarshallers();

        System.out.println("Complete constructing cache operations");

    }


    private  static void registerSchemasAndMarshallers() throws IOException {

        // Register entity marshallers on the client side ProtoStreamMarshaller instance associated with the remote cache manager.
        SerializationContext ctx = ProtoStreamMarshaller.getSerializationContext(cacheManager);
        ctx.registerProtoFiles(FileDescriptorSource.fromResources(PROTOBUF_DEFINITION_RESOURCE));
        ctx.registerMarshaller(new ExchangeMarshaller());

        // generate the 'memo.proto' schema file based on the annotations on Memo class and register it with the SerializationContext of the client
        ProtoSchemaBuilder protoSchemaBuilder = new ProtoSchemaBuilder();
        String memoSchemaFile = readResource(PROTOBUF_DEFINITION_RESOURCE);

        System.out.println(memoSchemaFile);

        // register the schemas with the server too
        RemoteCache<String, String> metadataCache = cacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
        // metadataCache.put(PROTOBUF_DEFINITION_RESOURCE, readResource(PROTOBUF_DEFINITION_RESOURCE));
        metadataCache.clear();
        metadataCache.put("exchange.proto", memoSchemaFile);
        String errors = metadataCache.get(ProtobufMetadataManagerConstants.ERRORS_KEY_SUFFIX);
        if (errors != null) {

            System.out.println(errors);

            throw new IllegalStateException("Some Protobuf schema files contain errors:\n" + errors);
        }
    }

    private static String readResource(String resourcePath) throws IOException {
        InputStream is = CacheOperations.class.getResourceAsStream(resourcePath);
        try {
            final Reader reader = new InputStreamReader(is, "UTF-8");
            StringWriter writer = new StringWriter();
            char[] buf = new char[1024];
            int len;
            while ((len = reader.read(buf)) != -1) {
                writer.write(buf, 0, len);
            }
            return writer.toString();
        } finally {
            is.close();
        }
    }

    public static RemoteCache<String, Object> getCache() {

        if (repoCache == null ) {

            try {
                setup();

                repoCache.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }

            repoCache = cacheManager.getCache("default");


        }

        return repoCache;
    }

    public static List<CamelExchange> getExchangeContents(){

        QueryFactory qf = Search.getQueryFactory(CacheOperations.getCache());
        Query query = qf.from(CamelExchange.class)
                .having("name").like("%").toBuilder()
                .build();

        System.out.println("query built ok"+ query.list().size());

        return query.list();
    }

}
