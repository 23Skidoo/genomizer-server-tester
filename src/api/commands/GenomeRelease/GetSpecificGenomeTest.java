package api.commands.GenomeRelease;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.Debug;
import model.ErrorLogger;
import requests.GetGenomeReleasesRequest;
import requests.GetGenomeSpecieReleasesRequest;
import requests.RetrieveExperimentRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class GetSpecificGenomeTest extends SuperTestCommand {
    private String specie;
    private String expected;

    public GetSpecificGenomeTest(String ident, String specie, String expected, boolean expectedResult) {
        super(ident, expectedResult);
        this.specie = specie;
        this.expected = expected;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new GetGenomeSpecieReleasesRequest(specie),
                    CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = CommandTester.conn.getResponseBody().contains(expected);
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
