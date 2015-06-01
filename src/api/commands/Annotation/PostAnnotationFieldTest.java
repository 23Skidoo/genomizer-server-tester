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
import responses.sysadmin.AddGenomeReleaseResponse;
import util.Constants;
import util.RequestException;

import java.io.IOException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class PostAnnotationFieldTest extends SuperTestCommand {
    private String[] categories;
    private String name;
    private Boolean forced;

    public PostAnnotationFieldTest(String ident, String name,
                                   String[] categories, boolean forced, boolean expectedResult) {
        super(ident, expectedResult);
        this.name = name;
        this.forced = forced;
        this.categories = categories;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new AddAnnotationRequest(
                            name, categories, forced),
                    CommandTester.token, Constants.JSON);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
