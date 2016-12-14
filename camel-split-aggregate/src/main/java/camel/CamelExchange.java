package camel;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoMessage;

/**
 * Created by venusurampudi on 12/4/16.
 */
@ProtoMessage(name = "exchange")
public class CamelExchange {

    private String name;

    private byte[] bytes;

    @ProtoDoc("@IndexedField")
    @ProtoField(number=1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ProtoDoc("@IndexedField")
    @ProtoField(number = 2)
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String toString(){

        return "Name is " + name + "bytes " + bytes.length;

    }

}
