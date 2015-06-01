package api.commands.Admin;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.ChangePasswordRequest;
import requests.UpdateUserRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by ens11afk on 2015-05-28.
 */
public class PutUserTest extends SuperTestCommand {
    private String oldPass;
    private String newPass;
    private String name;
    private String email;

    public PutUserTest(String ident, String oldPass, String newPass, String name, String email,
                            boolean expectedResult){
        super(ident, expectedResult);
        this.oldPass = oldPass;
        this.newPass = newPass;
        this.name = name;
        this.email = email;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new ChangePasswordRequest(oldPass,
                    newPass, name, email), CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200){
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
