package api.commands.GenomeRelease;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.GetGenomeReleasesRequest;
import requests.RetrieveExperimentRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class GetAllGenomeTest extends SuperTestCommand {
    private String expected;

    public GetAllGenomeTest(String ident, String expected, boolean expectedResult) {

        super(ident, expectedResult);
        this.expected = expected;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new GetGenomeReleasesRequest(),
                    CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = CommandTester.conn.getResponseBody().contains(expected);
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}

