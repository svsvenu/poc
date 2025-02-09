package com.venu;

public interface SuperInterface {

    public static int getNumber(){
        return 10;
    }

    public default int getDefaultNumber(){
        return 5;
    }

    public void abstractMethod();
}
