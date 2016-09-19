package com.venu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * The Class JUnitServiceTestExample.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExecuteLogicTest {

    @Mock
    private ExecuteLogic executeLogic;


    @Mock
    private Logic test;

@Before
    public void setUp(){

     //   ExecuteLogic executeLogic = mock(ExecuteLogic.class);

      //  Logic test = Mockito.mock(Logic.class);

        when(test.executeLogic(anyInt(),anyInt())).thenReturn(2);

        when(executeLogic.getAdvancedAdd() ).thenReturn(test);

        when(executeLogic.addNumbers(anyInt(), anyInt())).thenCallRealMethod();
    }

        @Test
    public void testSimpleInt() {

            System.out.println("started test");


            int output = executeLogic.addNumbers(10,3);

            System.out.println("Output is " + output);

            assert(3 == output);

            System.out.println("post assert " + output);


            //    executeLogic.getAdvancedAdd();
        // define return value for method getUniqueId()
     //   when(test.getUniqueId()).thenReturn(43);

        // use mock in test....
    //    assertEquals(test.getUniqueId(), 43);
    }

    /**
     * Test more than one return value.
     */

}