package api.commands.File;

import api.commands.CommandTester;
import api.commands.ConvertTests;
import api.commands.FileTests;
import api.commands.SuperTestCommand;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ErrorLogger;
import requests.DownloadFileRequest;
import util.Constants;
import util.FileData;
import util.RequestException;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class GetFileTest extends SuperTestCommand {
    private String expected;

    public GetFileTest(String ident, String expected, boolean expectedResult) {
        super(ident, expectedResult);
        this.expected = expected;

    }

    @Override
    public void execute() {

        try {
            CommandTester.conn.sendRequest(new DownloadFileRequest(CommandTester.fileID.getString(), ""),
                    CommandTester.token, Constants.TEXT_PLAIN);
            if (CommandTester.conn.getResponseCode() == 200) {
                super.finalResult = CommandTester.conn.getResponseBody().contains(expected);
            }

        } catch (RequestException e) {
            if (super.expectedResult) ErrorLogger.log(e);

        }


    }
}
