package api.commands.Experiment;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.AddExperimentRequest;
import util.AnnotationDataValue;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class PostExperimentTest extends SuperTestCommand {
    AnnotationDataValue[] adv;
    private String key;

    public PostExperimentTest(String ident, String key, AnnotationDataValue[] adv, boolean expectedResult) {
        super(ident, expectedResult);
        this.key = key;
        this.adv = adv;

    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new AddExperimentRequest(key, adv), CommandTester.token, "application/json");

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
