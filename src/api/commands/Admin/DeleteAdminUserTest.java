package api.commands.Admin;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.CreateUserRequest;
import requests.DeleteUserRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by ens11afk on 2015-05-28.
 */
public class DeleteAdminUserTest extends SuperTestCommand {
    private String username;

    public DeleteAdminUserTest(String ident, String username, boolean expectedResult){
        super(ident, expectedResult);
        this.username = username;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(
                    new DeleteUserRequest(username),
                    CommandTester.token, Constants.TEXT_PLAIN);
            if (CommandTester.conn.getResponseCode() == 200){
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
