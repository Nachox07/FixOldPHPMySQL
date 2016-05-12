package com.company;

/**
 * @author Nacho González-Garilleti <nachox07@users.noreply.github.com>
 */

public class Main {

    public static void main(String[] args) {

        try {

            FixOldPHPMySQL fopm = new FixOldPHPMySQL(args[0], args[1]);

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("The program need one argument with the path and another with the var name which contains mysql_connect");

        }

    }

}