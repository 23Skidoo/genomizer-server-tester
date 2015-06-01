package api.commands.Annotation;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import com.google.gson.Gson;
import model.ErrorLogger;
import requests.AddAnnotationRequest;
import requests.ChangeAnnotationRequest;
import requests.ChangeExperimentRequest;
import requests.RenameAnnotationFieldRequest;
import util.AnnotationDataValue;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class PutAnnotationFieldTest extends SuperTestCommand {
    private String oldName;
    private String newName;

    public PutAnnotationFieldTest(String ident, String oldName, String newName, boolean expectedResult) {
        super(ident, expectedResult);
        this.oldName = oldName;
        this.newName = newName;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new RenameAnnotationFieldRequest(
                            oldName, newName),
                    CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
