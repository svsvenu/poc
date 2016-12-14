package camel;

/**
 * Created by venusurampudi on 6/28/16.
 */

import org.infinispan.protostream.MessageMarshaller;
import java.io.IOException;
/**
 * @author Adrian Nistor
 */
public class ExchangeMarshaller implements MessageMarshaller<CamelExchange> {

    public String getTypeName() {
        return "quickstart.exchange";
    }

    public Class<CamelExchange> getJavaClass() {
        return CamelExchange.class;
    }

    public CamelExchange readFrom(ProtoStreamReader reader) throws IOException {

        String name = reader.readString("name");

        byte[] bytes = reader.readBytes("bytes");

        CamelExchange exchange = new CamelExchange();

        exchange.setName(name);

        exchange.setBytes(bytes);


        return exchange;
    }

    public void writeTo(ProtoStreamWriter writer, CamelExchange exchange) throws IOException {

        writer.writeString("name", exchange.getName());

        writer.writeBytes("bytes", exchange.getBytes());

    }
}