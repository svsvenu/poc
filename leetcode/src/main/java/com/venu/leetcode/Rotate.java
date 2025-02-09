package com.venu.leetcode;

public class Rotate {

    private static int[][] matrix = new int[3][3];

    public static void main(String[] args){

        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;

        matrix[1][0] = 4;
        matrix[1][1] = 5;
        matrix[1][2] = 6;

        matrix[2][0] = 7;
        matrix[2][1] = 8;
        matrix[2][2] = 9;



        System.out.println ("leet code begin");
        printMatrix();

    }

    private static void printMatrix(){
        System.out.println ("[");

        for ( int i=0; i<matrix.length; i++ ) {

            for ( int k=0 ; k< 3 ; k++) {
                System.out.print (matrix[i][k] + ", ");

            }
            System.out.println ("\n");

        }
        System.out.print ("]");


    }

    private stat

}
