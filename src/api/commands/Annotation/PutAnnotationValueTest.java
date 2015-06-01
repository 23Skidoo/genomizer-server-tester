package api.commands.Annotation;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.ChangeExperimentRequest;
import requests.RenameAnnotationFieldRequest;
import requests.RenameAnnotationValueRequest;
import util.AnnotationDataValue;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class PutAnnotationValueTest extends SuperTestCommand {
    private String annotationName;
    private String oldName;
    private String newName;

    public PutAnnotationValueTest(String ident, String annotationName,
                                  String oldName, String newName, boolean expectedResult) {
        super(ident, expectedResult);
        this.annotationName = annotationName;
        this.oldName = oldName;
        this.newName = newName;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new RenameAnnotationValueRequest(
                            annotationName, oldName, newName),
                    CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
