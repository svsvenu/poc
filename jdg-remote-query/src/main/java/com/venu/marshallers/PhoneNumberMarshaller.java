package com.venu.marshallers;

/**
 * Created by venusurampudi on 6/28/16.
 */

import org.infinispan.protostream.MessageMarshaller;
import com.venu.domain.PhoneNumber;
import com.venu.domain.PhoneType;

import java.io.IOException;

/**
 * @author Adrian Nistor
 */
public class PhoneNumberMarshaller implements MessageMarshaller<PhoneNumber> {

    @Override
    public String getTypeName() {
        return "quickstart.Person.PhoneNumber";
    }

    @Override
    public Class<PhoneNumber> getJavaClass() {
        return PhoneNumber.class;
    }

    @Override
    public PhoneNumber readFrom(ProtoStreamReader reader) throws IOException {
        String number = reader.readString("number");
        PhoneType type = reader.readObject("type", PhoneType.class);

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(number);
        phoneNumber.setType(type);
        return phoneNumber;
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, PhoneNumber phoneNumber) throws IOException {
        writer.writeString("number", phoneNumber.getNumber());
        writer.writeObject("type", phoneNumber.getType(), PhoneType.class);
    }
}