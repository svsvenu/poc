package com.venu;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by venusurampudi on 7/1/17.
 */
public class jsonmap {

    public static void main(String[] args) throws Exception{

        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally


        Map<String,Object> userData = mapper.readValue(new File("/Users/venusurampudi/git/poc/json-map/src/main/resources/test.json"), Map.class);

        ArrayList<Object> names = (ArrayList<Object>) userData.get("name");

        Map<String, Object> nameObj = (Map<String, Object>) names.get(0);

        System.out.println("object built out is " + nameObj.keySet());


    }


}
