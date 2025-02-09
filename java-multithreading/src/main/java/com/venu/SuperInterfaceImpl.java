package com.venu;

public class SuperInterfaceImpl implements SuperInterface {
    @Override
    public void abstractMethod() {

    }

    public static void main(String[] args){

        SuperInterface si = new SuperInterfaceImpl();

        System.out.println( si.getDefaultNumber() );
        System.out.println( SuperInterface.getNumber() );

    }
}
