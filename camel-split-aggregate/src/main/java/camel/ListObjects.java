package camel;

import com.HolidayRequest;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

import javax.xml.stream.XMLInputFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Created by venusurampudi on 12/12/16.
 */
public class ListObjects {

    public static void main(String[] args){

        // queryPersonByPhone();

        parseXML();
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

    private static void parseXML(){

        try {


            XMLInputFactory factory = XMLInputFactory.newInstance();

            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File("/Users/venusurampudi/Desktop/temp/.camel/employee.txt")));

            while(!reader.isStartElement()){

                reader.next();
            }

            while(!reader.getLocalName().equals("HolidayRequest")){

                reader.next();
            }

            System.out.println("Pointer at next " );


                if (reader.isStartElement() && reader.getLocalName().equals("HolidayRequest")) {

                JAXBContext jc = JAXBContext.newInstance(HolidayRequest.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();

                    System.out.println("Befor parsing  " );



                    JAXBElement<HolidayRequest> jb = unmarshaller.unmarshal(reader, HolidayRequest.class);

               System.out.println("next returning" + jb.getValue());

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }


    }

}
