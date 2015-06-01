package api.commands.Login;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import api.commands.TestCollection;
import model.ErrorLogger;
import requests.LoginRequest;
import requests.LogoutRequest;
import responses.LoginResponse;
import responses.ResponseParser;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-28.
 */
public class LogoutTest extends SuperTestCommand{

    public LogoutTest(String ident, boolean expectedResult) {
        super(ident, expectedResult);
      }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new LogoutRequest(), CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
