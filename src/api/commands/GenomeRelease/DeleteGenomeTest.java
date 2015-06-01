package api.commands.GenomeRelease;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.RemoveExperimentRequest;
import requests.RemoveGenomeReleaseRequest;
import util.Constants;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class DeleteGenomeTest extends SuperTestCommand {
    private String version;
    private String species;

    public DeleteGenomeTest(String ident, String species, String version, boolean expectedResult) {
        super(ident, expectedResult);
        this.species = species;
        this.version = version;
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new RemoveGenomeReleaseRequest(species, version),
                    CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = true;
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }
}
