package com;

import java.util.Optional;

public class venu {

    public static void main(String[] args){

        String venu = null;

        Optional<String> gender = Optional.ofNullable(venu);
        Optional<String> emptyGender = Optional.empty();

     //   System.out.println(gender.orElse("<N/A>")); //MALE
      //  System.out.println(emptyGender.orElse("<N/A>")); //<N/A>

      //  System.out.println(gender.orElseGet(() -> "<N/A>")); //MALE
        System.out.println("gender " + Optional.ofNullable(venu).orElse(null)); //<N/A>

    }
}
