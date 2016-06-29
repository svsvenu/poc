package com.venu.marshallers;

/**
 * Created by venusurampudi on 6/28/16.
 */

import org.infinispan.protostream.EnumMarshaller;
import com.venu.domain.PhoneType;

/**
 * @author Adrian Nistor
 */
public class PhoneTypeMarshaller implements EnumMarshaller<PhoneType> {

    @Override
    public Class<PhoneType> getJavaClass() {
        return PhoneType.class;
    }

    @Override
    public String getTypeName() {
        return "quickstart.Person.PhoneType";
    }

    @Override
    public PhoneType decode(int enumValue) {
        switch (enumValue) {
            case 0:
                return PhoneType.MOBILE;
            case 1:
                return PhoneType.HOME;
            case 2:
                return PhoneType.WORK;
        }
        return null;
    }

    @Override
    public int encode(PhoneType phoneType) {
        switch (phoneType) {
            case MOBILE:
                return 0;
            case HOME:
                return 1;
            case WORK:
                return 2;
        }

        throw new IllegalArgumentException("Unexpected PhoneType value : " + phoneType);
    }
}