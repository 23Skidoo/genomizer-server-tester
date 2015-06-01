package model;

import java.text.Format;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class Debug {

    public static void log(String message, boolean status) {
        if(status){
            System.out.flush();
            System.out.printf("TEST: %-30.30s  STATUS: %-30.30s%n",message, "SUCCESS");
            System.out.flush();
        }else{
            System.err.flush();
            System.err.printf("TEST: %-30.30s  STATUS: %-30.30s%n",message, "FAIL");
            System.err.flush();
        }
    }
}
