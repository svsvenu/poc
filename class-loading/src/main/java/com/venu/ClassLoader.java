package com.venu;

/**
 * Created by venusurampudi on 9/26/16.
 */
public class ClassLoader {

    public static void main(String[] args){

        String input = "aaaaaaaaaaaa";

        System.out.println("input " + input);

        String output = subString(input, 5);

        System.out.println(input);

        System.out.println(output);

    }


    private static String subString(String input, int beginIndex){

        input = input.substring(beginIndex);

        String retVal = input.substring(0, beginIndex);

        return retVal;


    }


}
