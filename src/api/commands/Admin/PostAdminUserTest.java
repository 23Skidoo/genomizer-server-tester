package api.commands.Admin;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.CreateUserRequest;
import util.Constants;
import util.RequestException;

/**
 * Contains the command for creating users.
 *
 * @author c10mjn, ens11afk, c12slm
 * @version 1.0
 * 03 June 2015
 *
 */
public class PostAdminUserTest extends SuperTestCommand {

    private String username;
    private String password;
    private String privileges;
    private String name;
    private String email;

    public PostAdminUserTest(String ident, String username, String password,
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
                    new CreateUserRequest(username,password,privileges,name,email),
                    CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200){
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
