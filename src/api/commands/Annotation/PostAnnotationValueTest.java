package api.commands.Annotation;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import communication.HTTPURLUpload;
import model.Debug;
import model.ErrorLogger;
import requests.AddAnnotationRequest;
import requests.AddGenomeReleaseRequest;
import requests.AddNewAnnotationValueRequest;
import responses.sysadmin.AddGenomeReleaseResponse;
import util.Constants;
import util.RequestException;

import java.io.IOException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class PostAnnotationValueTest extends SuperTestCommand {
    private String annotationLabel;
    private String annotationValue;

    public PostAnnotationValueTest(String ident, String annotationLabel, String annotationValue, boolean expectedResult) {
        super(ident, expectedResult);
        this.annotationLabel = annotationLabel;
        this.annotationValue = annotationValue;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new AddNewAnnotationValueRequest(annotationLabel, annotationValue),
                    CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
