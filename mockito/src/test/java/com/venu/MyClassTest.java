package com.venu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by venusurampudi on 4/18/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {


    @Test
    public void MyClassTest() {

        MyClass mc = Mockito.spy(new MyClass("a","b","c") );

        Mockito.doReturn("mock").when(mc.executeLogic("real"));

        Mockito.doReturn("externalString").when(mc.getStringFromExternalSources());

        System.out.println(mc.executeLogic("real"));

    }

}
