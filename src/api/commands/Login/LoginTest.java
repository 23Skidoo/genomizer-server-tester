package api.commands.Login;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import api.commands.TestCollection;
import model.ErrorLogger;
import requests.LoginRequest;
import responses.LoginResponse;
import responses.ResponseParser;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-28.
 */
public class LoginTest extends SuperTestCommand {
    private String username;
    private String password;

    public LoginTest(String ident, String username, String password, boolean expectedResult) {
        super(ident, expectedResult);
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new LoginRequest(this.username, this.password), "", "application/json");

            LoginResponse loginResponse = ResponseParser.parseLoginResponse(CommandTester.conn.getResponseBody());
            if (loginResponse != null) {
                CommandTester.token = loginResponse.token;
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult){
                ErrorLogger.log(e);
            }
        }
    }
}
