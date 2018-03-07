package com.venu;

/**
 * Created by venusurampudi on 3/6/18.
 */
public class MaliciousPerson extends Person implements java.io.Serializable{

    long serialVersionUID = 5839387530557351751l;

    public MaliciousPerson() {

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
        return "I am Heisenberg";
    }




}
