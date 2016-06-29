package com.venu.domain;

/**
 * Created by venusurampudi on 6/28/16.
 */
public class PhoneNumber {
    private String number;
    private PhoneType type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}
