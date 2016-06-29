package com.venu.domain;

import java.util.List;

/**
 * Created by venusurampudi on 6/28/16.
 */
public class Person {


    private String name;
    private int id;
    private String email;
    private List<PhoneNumber> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumber> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name +
                "', email='" + email + '\'' +
                ", phones=" + phones +
                '}';
    }
}
