package api.commands.File;

import api.commands.CommandTester;
import api.commands.FileTests;
import api.commands.StringContainer;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.RemoveFileFromExperimentRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class DeleteFileTest extends SuperTestCommand {
    private StringContainer file;

    public DeleteFileTest(String ident, StringContainer file, boolean expectedResult) {
        super(ident, expectedResult);
        this.file = file;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new RemoveFileFromExperimentRequest(file.getString()), CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}