package com.venu;

/**
 * Created by venusurampudi on 3/6/18.
 */
public class Person  implements java.io.Serializable {

    public Person() {

    }

    private String name;

    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String toString() {

        // System.exit(0);
        return "I am walter white";
    }

}
