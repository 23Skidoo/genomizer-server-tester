package api.commands.Annotation;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.RemoveAnnotationFieldRequest;
import requests.RemoveGenomeReleaseRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class DeleteAnnotationFieldTest extends SuperTestCommand {
    private String name;


    public DeleteAnnotationFieldTest(String ident, String name, boolean expectedResult) {
        super(ident, expectedResult);
        this.name = name;
    }

    @Override
    public void execute() {

        try {
            CommandTester.conn.sendRequest(new RemoveAnnotationFieldRequest(name),
                    CommandTester.token, Constants.TEXT_PLAIN);
            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
