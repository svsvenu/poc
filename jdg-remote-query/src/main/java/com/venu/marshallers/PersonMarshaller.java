package com.venu.marshallers;

/**
 * Created by venusurampudi on 6/28/16.
 */

import org.infinispan.protostream.MessageMarshaller;
import com.venu.domain.Person;
import com.venu.domain.PhoneNumber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Nistor
 */
public class PersonMarshaller implements MessageMarshaller<Person> {

    @Override
    public String getTypeName() {
        return "quickstart.Person";
    }

    @Override
    public Class<Person> getJavaClass() {
        return Person.class;
    }

    @Override
    public Person readFrom(ProtoStreamReader reader) throws IOException {
        String name = reader.readString("name");
        int id = reader.readInt("id");
        String email = reader.readString("email");
        List<PhoneNumber> phones = reader.readCollection("phone", new ArrayList<PhoneNumber>(), PhoneNumber.class);

        Person person = new Person();
        person.setName(name);
        person.setId(id);
        person.setEmail(email);
        person.setPhones(phones);
        return person;
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Person person) throws IOException {
        writer.writeString("name", person.getName());
        writer.writeInt("id", person.getId());
        writer.writeString("email", person.getEmail());
        writer.writeCollection("phone", person.getPhones(), PhoneNumber.class);
    }
}