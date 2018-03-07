package com.venu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by venusurampudi on 9/26/16.
 */
public class Deserialize {

    private static String fileName = "/Users/venusurampudi/git/poc/deserialize/src/main/java/com/venu/output.ser";

    public static void main(String[] args){

        Person goodPerson = new Person();

        System.out.println("Good Object's toString said " + goodPerson.toString() );

        MaliciousPerson myPerson = new MaliciousPerson();

            myPerson.setName("venu");

            myPerson.setJob("myJob");

        serializeObject(myPerson);

        deSerializeObject();

    }

    private static void serializeObject(Object o) {

        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(o);

            out.close();
            file.close();


        }

        catch(Exception ex)
        {

                ex.printStackTrace();
        }



    }


    private static void deSerializeObject() {

        try
        {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            Person object = (Person)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized its toString said " + object.toString());

        }

        catch(Exception ex)
        {

            ex.printStackTrace();
        }

    }

}
