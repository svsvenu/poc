package com.venu;

/**
 * Created by venusurampudi on 4/18/18.
 */
public class MyClass {

    String a,b,c;

    public MyClass(String a, String b, String c) {


        this.a = a;

        this.b =  b;

        this.c = c;


    }

    public String  executeLogic (String d) {

        return a + b + c + d;

    }


    public String getStringFromExternalSources (){

        return "i got it from some place else";

    }

}
