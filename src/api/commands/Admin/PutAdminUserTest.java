package api.commands.Admin;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.UpdateUserRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by ens11afk on 2015-05-28.
 */
public class PutAdminUserTest extends SuperTestCommand {
    private String username;
    private String password;
    private String privileges;
    private String name;
    private String email;

    public PutAdminUserTest(String ident, String username, String password,
                            String privileges, String name, String email, boolean expectedResult){
        super(ident, expectedResult);
        this.username = username;
        this.password = password;
        this.privileges = privileges;
        this.name = name;
        this.email = email;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(
                    new UpdateUserRequest(username,password,privileges,name,email),
                    CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200){
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
