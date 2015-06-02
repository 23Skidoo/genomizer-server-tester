package api.commands;

import api.commands.Experiment.DeleteExperimentTest;
import api.commands.Login.LoginTest;
import api.commands.Login.LogoutTest;
import communication.Connection;
import model.Debug;
import model.ErrorLogger;
import requests.LoginRequest;
import requests.LogoutRequest;
import responses.LoginResponse;
import responses.ResponseParser;
import util.Constants;
import util.RequestException;



/**
 * Created by c10mjn on 2015-05-25.
 */
public class CommandTester {
    public static String token =  "";
    private String role;
    public static Connection conn;

    public static StringContainer fileID;

    public static String EXP_NAME = "testExp1";


    public CommandTester (Connection connection){
        for(int i = 0; i < TestCollection.laps; i++){
            conn = connection;

            LoginTests lt = new LoginTests();
            lt.execute();

            new LoginTest("POST LOGIN", "testadmin", "baguette", true).execute();

            //Test experiments (POST, GET, PUT, DELETE)
            ExperimentTests e = new ExperimentTests();
            e.execute();

            //Test FILE (POST, GET, DELETE)
            FileTests ft = new FileTests();
            ft.execute();

            //CONVERT (PUT)
            ConvertTests ct = new ConvertTests();
            ct.execute();

            //SEARCH (GET)
            SearchTests st = new SearchTests();
            st.execute();

            //USER
            UserTests ut = new UserTests();
            ut.execute();

            //ADMIN (POST, PUT, DELETE)
            AdminTests at = new AdminTests();
            at.execute();

            //PROCESSING
            ProcessingTests pt = new ProcessingTests();


            //ANNOTATION (POST, PUT, DELETE, GET)
            AnnotationTests a = new AnnotationTests();
            a.execute();

            //GENOME HANDLING (POST, PUT, DELETE, GET)
            GenomeReleaseTests g = new GenomeReleaseTests();
            g.execute();

            DeleteExperimentTest de = new DeleteExperimentTest("FINAL CLEANUP", CommandTester.EXP_NAME, true);
            de.execute();

            Debug.log(de.toString() , de.finalResult == de.expectedResult);

            new LogoutTest("DELETE LOGIN", true).execute();


        }

        System.out.println("\n-------------------------------------------------");
        System.out.println("Total tests run: " + TestCollection.runTests);
        System.out.println("Successful tests: " + TestCollection.succeededTests);
        System.out.println("Failed tests: " + TestCollection.failedTests);
        System.out.println("-------------------------------------------------");
        System.out.println("Failed:\n");
        for(String s : TestCollection.nameOfFailedTests){
            System.out.println(s);
        }
        System.out.println("-------------------------------------------------");
    }
}
