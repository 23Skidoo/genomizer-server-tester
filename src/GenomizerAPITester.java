import api.commands.CommandTester;
import communication.Connection;
import communication.ConnectionFactory;
import communication.SSLTool;
import model.ErrorLogger;
import requests.LoginRequest;
import requests.LogoutRequest;
import responses.LoginResponse;
import responses.ResponseParser;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-25.
 */
public class GenomizerAPITester {
    public static String token;
    public static void main(String[] args){

        // path to server
        if (args.length != 1) {
            System.err.println("Start with path to server and experiment name.");
            System.exit(-1);
        }
        ConnectionFactory cf = new ConnectionFactory();
        cf.setIP(args[0]);

        SSLTool.disableCertificateValidation();
        Connection c = cf.makeConnection();
        new CommandTester(c);
        //Do sequential testing
    }
}
