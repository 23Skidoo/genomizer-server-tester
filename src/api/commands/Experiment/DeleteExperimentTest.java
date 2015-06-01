package api.commands.Experiment;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.RemoveExperimentRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class DeleteExperimentTest extends SuperTestCommand {
    private String key;


    public DeleteExperimentTest(String ident, String key, boolean expectedResult) {
        super(ident, expectedResult);
        this.key = key;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new RemoveExperimentRequest(key), CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
