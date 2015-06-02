package api.commands.Admin;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.GetUserNamesRequest;
import responses.ResponseParser;
import util.Constants;
import util.FileData;
import util.RequestException;
import util.UserList;

import java.util.ArrayList;

/**
 * Created by ens11afk on 2015-06-02.
 */
public class GetUserList extends SuperTestCommand{

    public static UserList usernames = new UserList();
    private String expected;

    public GetUserList(String ident, String expected, boolean expectedResult) {
        super(ident, expectedResult);
        this.expected = expected;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new GetUserNamesRequest(),
                    CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                usernames = ResponseParser.parseUserList(CommandTester.conn.getResponseBody());

                super.finalResult = true;

                if (!expected.equals(""))
                    super.finalResult = usernames.userExist(expected);
            }

        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
