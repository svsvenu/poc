package com.venu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static void main(String[] args){

        Pattern p = Pattern.compile("[\\x46]");

        Matcher m = p.matcher("F");

        while (m.find()) {

          System.out.println(   m.group());
        }




    }
}
