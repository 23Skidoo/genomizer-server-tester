package api.commands.File;

import api.commands.CommandTester;
import api.commands.FileTests;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.UpdateFileRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class PutFileTest extends SuperTestCommand {
    private String expected;
    private FileTuple ft;

    public PutFileTest(String ident, FileTuple ft, String expected, boolean expectedResult) {
        super(ident,expectedResult);
        this.expected = expected;
        this.ft = ft;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new UpdateFileRequest(ft.getId()), CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = CommandTester.conn.getResponseBody().contains(expected);
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
//            System.out.println(CommandTester.conn.getResponseCode() + " , " + CommandTester.conn.getResponseBody());
        }
    }
}
