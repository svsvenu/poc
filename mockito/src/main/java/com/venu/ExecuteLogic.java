package com.venu;

/**
 * Created by venusurampudi on 9/18/16.
 */
public class ExecuteLogic {

    public int addNumbers(int a, int b){

        int ret = 5;

        Logic logic = getAdvancedAdd();

        int sum = logic.executeLogic(a,b);

        System.out.println("Sum is " + sum);

        return sum;
    }

    public Logic getAdvancedAdd(){

        return new Logic();
    }
}
