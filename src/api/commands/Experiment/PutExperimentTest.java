package api.commands.Experiment;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.ChangeExperimentRequest;
import util.AnnotationDataValue;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class PutExperimentTest extends SuperTestCommand {
    String key;
    AnnotationDataValue[] adv;

    public PutExperimentTest(String ident, String key, AnnotationDataValue[] advIN, boolean expectedResult) {
        super(ident, expectedResult);
        this.key = key;
        this.adv = advIN;
    }

    @Override
    public void execute() {
            try {
                CommandTester.conn.sendRequest(new ChangeExperimentRequest(key, this.adv), CommandTester.token, "application/json" );

                if (CommandTester.conn.getResponseCode() == 200) {
                    super.finalResult = true;
                }
            } catch (RequestException e) {
                if (super.expectedResult) ErrorLogger.log(e);
            }
    }
}
