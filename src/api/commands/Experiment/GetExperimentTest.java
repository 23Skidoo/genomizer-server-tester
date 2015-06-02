package api.commands.Experiment;

import api.commands.CommandTester;
import api.commands.SuperTestCommand;
import model.ErrorLogger;
import requests.RetrieveExperimentRequest;
import responses.ResponseParser;
import util.Constants;
import util.ExperimentData;
import util.FileData;
import util.RequestException;

import java.util.ArrayList;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class GetExperimentTest extends SuperTestCommand {
    private String expected;
    private String key;
    private ArrayList<String> fileID;

    public GetExperimentTest(String ident, String key, String expected, boolean expectedResult) {
        super(ident, expectedResult);
        this.key = key;
        this.expected = expected;
        this.fileID = new ArrayList<>();
    }

    @Override
    public void execute() {
        try {
            CommandTester.conn.sendRequest(new RetrieveExperimentRequest(key),
                    CommandTester.token, Constants.TEXT_PLAIN);

            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = CommandTester.conn.getResponseBody().contains(expected);

                ExperimentData ed = ResponseParser.parseRetrieveExp(CommandTester.conn.getResponseBody());
                for(FileData fd : ed.files) {
                    fileID.add(fd.id);
                }
            }
        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);
        }
    }

    public String getFileID(int index){

        if (fileID.size() == 0)
            return "";

        return this.fileID.get(index);
    }
}
