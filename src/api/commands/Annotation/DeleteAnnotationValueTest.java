package api.commands.Annotation;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.RemoveAnnotationFieldRequest;
import requests.RemoveAnnotationValueRequest;
import requests.RemoveGenomeReleaseRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class DeleteAnnotationValueTest extends SuperTestCommand {
    private String name;
    private String value;

    public DeleteAnnotationValueTest(String ident, String name, String value, boolean expectedResult) {
        super(ident, expectedResult);
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new RemoveAnnotationValueRequest(
                            name, value),
                    CommandTester.token, Constants.TEXT_PLAIN);
            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
