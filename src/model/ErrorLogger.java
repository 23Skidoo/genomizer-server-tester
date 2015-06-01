package model;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class ErrorLogger {

    public static void log(String message){
        System.err.println("Error: " + "<"  + message + ">");
    }

    public static void log(Exception e){
        log(e.getMessage());
    }
}



